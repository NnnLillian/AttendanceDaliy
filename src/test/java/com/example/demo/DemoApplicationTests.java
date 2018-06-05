package com.example.demo;

import com.example.demo.entity.*;
import com.example.demo.mapper.AttendanceMapper;
import com.example.demo.mapper.CourseInfoMapper;
import com.example.demo.mapper.RelationsMapper;
import com.example.demo.mapper.StudentInfoMapper;
import com.example.demo.service.CourseService;
import com.example.demo.service.impl.CourseServiceimpl;
import org.apache.ibatis.javassist.NotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.matchers.Null;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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

}
