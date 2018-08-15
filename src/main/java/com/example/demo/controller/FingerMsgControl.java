package com.example.demo.controller;

import org.apache.catalina.Host;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

import static java.lang.System.out;
import static jdk.nashorn.internal.runtime.PropertyDescriptor.GET;
import static org.springframework.http.HttpHeaders.FROM;
import static org.springframework.http.HttpHeaders.HOST;


@Controller
@RequestMapping("/iclock")
public class FingerMsgControl {

    //1. 设备启动时，从服务器上读取设备的配置信息
    @GetMapping("/cdata")
    public String getConnect(@RequestParam("SN") String SN,
                             @RequestParam("options") String options,
                             @RequestParam("pushver") String pushver,
                             @RequestParam("language") int language,
                             HttpServletRequest request,
                             HttpServletResponse response){
//                             HttpServletResponse response) throws IOException {
//        SN = "3485162300120";
//        language = 83;
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
            rec += "TransTimes=00:00;14:00" + "\n";
            //  客户端检查并传送新数据间隔时间(分钟)
            rec += "TransInterval=1" + "\n";
            //  客户端向服务器自动上传哪些数据的标识
        //        rec += "TransFlag=1000000000"+"\n";
            rec += "TransFlag=TransData" + "\t" + "AttLog" + "\n";
            rec += "TimeZone=8" + "\n";
            //  客户端实时传送新记录
            rec += "Realtime=0" + "\n";
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
}