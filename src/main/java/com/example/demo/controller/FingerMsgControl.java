package com.example.demo.controller;

import com.example.demo.entity.Relations;
import com.example.demo.entity.StudentInfo;
import com.example.demo.service.CourseService;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

import static java.lang.System.out;


@Controller
@RequestMapping("/iclock")
public class FingerMsgControl {

    @Autowired
    private CourseService courseService;
    @Autowired
    private StudentService studentService;

    //1. 设备启动时，从服务器上读取设备的配置信息
    @GetMapping("/cdata")
    public String getConnect(@RequestParam("SN") String SN,
                             @RequestParam("options") String options,
                             @RequestParam("pushver") String pushver,
                             @RequestParam("language") int language,
                             HttpServletRequest request,
                             HttpServletResponse response){
        try {
            out.println("Msg is running");
            out.println(SN);
            String rec = "GET OPTION FROM: ";
            rec += SN + "\n";
            rec += "ATTLOGStamp=9999" + "\n";
            //  联网失败后客户端重新联接服务器的间隔时间(秒)
            rec += "ErrorDelay=60" + "\n";
            //  正常联网时客户端联接服务器的间隔时间
            rec += "Delay=30" + "\n";
            //  客户端定时检查并传送新数据时间
            rec += "TransTimes=00:00;12:00" + "\n";
            //  客户端检查并传送新数据间隔时间(分钟)
            rec += "TransInterval=1" + "\n";
            //  客户端向服务器自动上传哪些数据的标识
        //        rec += "TransFlag=1000000000"+"\n";
            rec += "TransFlag=TransData" + "\t" + "AttLog" + "\n";
            rec += "TimeZone=8" + "\n";
            //  客户端实时传送新记录为1表示有新数据就传送到服务器，为0 表示按照 TransTimes 和 TransInterval 规定的时间传送
            rec += "Realtime=1" + "\n";
            //  是否加密传送数据，保留未使用
            rec += "Encrypt=None" + "\n";
            //  服务器支持的协议版本号及时间
        //        rec += "ServerVer=3.4.1 2010\u00AD06\u00AD07";
//                rec += "ServerVer="+pushver;


            // HTTP响应头域
            String host = request.getHeader("Host");
            response.addHeader("Host", host);
            // 返回时间
            response.addHeader("Date", new Date().toString());
            // 设置内容类型
            response.addHeader("Content-Type", "text/plain");
            // 返回长度
            response.addHeader("Content-Length", "190");

            response.getOutputStream().write(rec.getBytes());

            out.flush();
            out.close();
            response.flushBuffer();


            //    throw new IOException().printStackTrace();

                return rec;
        }catch (IOException e){
            e.printStackTrace();
            return "-1";
        }


    }



//    4. 上传考勤信息
    @RequestMapping(value = "/cdata", method = RequestMethod.POST)
    public @ResponseBody
    HttpServletResponse getAttendance(@RequestParam("SN") String SN,
                         @RequestParam("table") String table,
                         @RequestParam("Stamp") String Stamp,
                         @RequestBody String attLog,
                         HttpServletRequest request,
                         HttpServletResponse response){
        try{
            System.out.println("getAttendance is running");
            System.out.println(SN);
            System.out.println(attLog);
            String line[] = attLog.split("\n");
            int length = line.length;
            int cId=0;
            char SerialNumber = SN.charAt(12);
            switch (SerialNumber){
                case '5' :
                    cId = 2;
                    break;
            }
            int i = 0;
            System.out.println(cId);
            while (i < length){
//                System.out.println(line[i-1]);
                String thisLine = line[i];
                String item[]=thisLine.split("\t");
                String pin = item[0];
                int uId = Integer.parseInt(pin);
                String time = item[1];
                Timestamp arriveTime = Timestamp.valueOf(time);
                i++;
                // 检查该学生是否选择这门课，若没有选择，则忽略该条打卡信息
                Relations a = courseService.selectRelationBycId_uId(uId, cId);
                if (a == null){
                    continue;
                }
                // 将签到数据写入数据库
                Timestamp leaveTime = null;
                String attComment = null;
                studentService.insertAttendance(uId, cId, arriveTime, leaveTime, attComment);
                // 给学生减少Last课时并增加ed
                int id = a.getId();
                int cNumberLast = a.getcNumberLast();
                int cNumberEd = a.getcNumberEd();
                cNumberLast = cNumberLast - 1;
                cNumberEd = cNumberEd + 1;
                courseService.updateCourseNumber(id, cNumberLast);
                courseService.updateCoursedNumber(id,cNumberEd);
                // 给家长发送短信
                StudentInfo oneStudent = studentService.selectStudentById(uId);
                String uName = oneStudent.getuName();
                String uConnect = oneStudent.getuConnect();
                String cName = courseService.selectCourseInfoById(cId).getcName();
                Date overDate = a.getOverDate();
                studentService.sendMessage(cName,uConnect,uName,time,cNumberLast,overDate);
            }

            // HTTP响应头域
            String host = request.getHeader("Host");
            response.addHeader("Host", host);
            // 返回时间
            response.addHeader("Date", new Date().toString());
            // 设置内容类型
            response.addHeader("Content-Type", "text/plain");
            // 返回长度
            response.addHeader("Content-Length", "4");

            // 返回成功处理的记录条数
            String rec = "OK:";
            rec += String.valueOf(length);
            response.getOutputStream().write(rec.getBytes());
            out.flush();
            out.close();
            response.flushBuffer();

            return response;

        }catch (IOException e) {
            e.printStackTrace();
            return response;
        }
    }
}