package com.example.demo;

import com.example.demo.entity.*;
import com.example.demo.mapper.AttendanceMapper;
import com.example.demo.mapper.CourseInfoMapper;
import com.example.demo.mapper.RelationsMapper;
import com.example.demo.mapper.StudentInfoMapper;
import com.example.demo.service.CourseService;
import com.example.demo.service.impl.CourseServiceimpl;
import com.example.demo.service.impl.StudentServiceimpl;
import org.apache.catalina.servlet4preview.ServletContext;
import org.apache.ibatis.javassist.NotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.matchers.Null;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.PostMapping;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    private RelationsMapper rMapper;
    @Autowired
    private StudentInfoMapper stuMapper;
    @Autowired
    private AttendanceMapper attMapper;
    @Autowired
    private CourseInfoMapper couMapper;
    @Autowired
    private StudentServiceimpl stuService;

//    @Test
//    public void CourseCountUpdateTest() {
//
//        Relations r = rMapper.selectRelationBycId_uId(1, 1);
//        r.setcNumberLast(r.getcNumberEd() + 1);
//        rMapper.updateCourseNumber(r);
//
//    }

    @Test public void checkAttendance() {

        Date now = new Date();
        Date from = new Date(now.getTime() - 1000 );
        Timestamp end = new java.sql.Timestamp(now.getTime());
        Timestamp start = new java.sql.Timestamp(from.getTime());
        System.out.println(end.toString());
        Attendance b = attMapper.selectAttendanceRecord(start, end, 1);

    }


    @Test
    public void queryStudentInfo() throws NullPointerException {

        String keyword = "%294%";

        List<StudentInfo> studentInfos = stuMapper.searchStudent(keyword,0,10);

        if (studentInfos.size() == 0) {
            throw new NullPointerException();
        }
        for (StudentInfo s : studentInfos) {
            System.out.println(s.getuName());
        }

    }

    @Test
    public void studentUpdateTest() {

        StudentInfo s = stuMapper.selectStudentById(1);
        s.setuConnect("12345678901");
        stuMapper.updateOneStudent(s);

    }

    @Test
    public void getStudentIdByCourse(){
        List<StudentInfo> a = stuMapper.selectStudentByCourseId(1);
        for(StudentInfo s : a) {
            System.out.println(s.getuName());
        }
        List<Relations> b = rMapper.selectRelationByCid(1);
        for (Relations f:b){
            System.out.println(f.getcNumberEd());
        }

    }

    @Test
    public void changeStudentInfoById(){
        StudentInfo a = stuMapper.selectStudentById(1);
        int uId = a.getuId();
        stuMapper.updateOneStudentFingerId(uId,20);
        a = stuMapper.selectStudentById(1);
        System.out.println(a.getuFingerId());
    }

    @Test
    public void changeStudentCourseInfoById(){
        StudentInfo a = stuMapper.selectStudentById(1);

        List<Relations> relations = rMapper.selectRelationByUid(a.getuId());
        rMapper.updateCoursedNumber(relations.get(0).getId(),20);

        Relations m = rMapper.selectRelationBycId_uId(a.getuId(),1);
        assert m.getcNumberEd() == 20;
        System.out.println(m.getcNumberEd());
    }

    @Test
    public void addCourseForStudent() {
        Date date = new Date();
        rMapper.insertCourseForStudent(1,9,20,date);
        Relations a = rMapper.selectRelationBycId_uId(1,9);
        System.out.println(a);
    }

    @Test
    public void insertStudent(){
        StudentInfo studentInfo = new StudentInfo();
        studentInfo.setuName("月月");
        studentInfo.setuConnect("1282940392");
        studentInfo.setuFingerId(20);
        stuMapper.insertStudentInfo(studentInfo);

    }

    @Test
    public void ImageUploadTest() {

        System.out.println(System.getProperty("user.dir"));

        String fname="aaabbb.jpg";
        fname += System.currentTimeMillis();
        fname = "" + fname.hashCode() + ".jpg";

        try {
            FileWriter writer = new FileWriter("./src/main/resources/static/img/"+fname);
            writer.write("");
            writer.close();

        }catch (IOException e) {

        }

    }


    @Test
    public void orderStudent(){
        List<relationTableSearchResultItem> rod = stuMapper.orderStudentByCourseNumber(1,"cNumberLast", "desc", 0,10);
        System.out.println(rod.size());
    }

    @Test
    public void selectStudentWithNullCourse(){
        List<Relations> r = rMapper.selectStudentIdWithLittleCourse();
        for(Relations s : r) {
            System.out.println(s.getuId());
            int cId = s.getcId();
            CourseInfo course = couMapper.selectCourseInfoById(cId);
            String cName = course.getcName();
            int uId = s.getuId();
            StudentInfo a = stuMapper.selectStudentById(uId);
            String uConnect = a.getuConnect();
            stuService.sendMessage(cName,uConnect);

        }

    }

    @Test
    public void sendMessage(){

        final String url="http://47.98.207.80:8888/sms.aspx";

        try {

            URL realURL= new URL(url);
            // 打开连接
            URLConnection conn = realURL.openConnection();
            // 设置请求头部
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 实现写字符流的功能
            PrintWriter out=new PrintWriter(conn.getOutputStream());
            //  action=send&userid=12&account=账号&password=密码&mobile=15023239810,13527576163&content=内容&sendTime=&extno=
            String query="";
            query+="action=send" + "&";
            query+="userid=262" + "&";
            query+="account=艺术培训" + "&";
            query+="password=123456" + "&";
            query+="mobile=15074808385" + "&";
            query+="content=成功" + "&";
            query+="sendTime=" + "&";
            query+="extno=";

            out.write(query);
            out.flush();
            // 接收响应数据
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }

            out.close();
            in.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void changeCourseLastByCsv() throws IOException {
        int cId = 1;
        System.out.println(System.getProperty("user.dir"));
        String root = System.getProperty("user.dir");
        String path = root + File.separator + "target" + File.separator + "classes" + File.separator + "folders" + File.separator + "1_attlog.dat";
        BufferedReader reader = new BufferedReader(new FileReader(path));
        String line = null;
        while((line=reader.readLine())!=null){
            String item[] = line.split("\t");
            int uId = Integer.parseInt(item[0].trim());
            Relations a = rMapper.selectRelationBycId_uId(uId, cId);
            int id = a.getId();
            int cNumberLast = a.getcNumberLast();
            cNumberLast = cNumberLast - 1;
            rMapper.updateCourseNumber(id, cNumberLast);
        }
    }

}
