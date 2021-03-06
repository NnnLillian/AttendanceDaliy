package com.example.demo.service.impl;

import com.example.demo.entity.Attendance;
import com.example.demo.entity.Relations;
import com.example.demo.entity.StudentInfo;
import com.example.demo.entity.relationTableSearchResultItem;
import com.example.demo.mapper.AttendanceMapper;
import com.example.demo.mapper.RelationsMapper;
import com.example.demo.mapper.StudentInfoMapper;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class StudentServiceimpl implements StudentService{
    @Autowired
    private StudentInfoMapper studentInfoMapper;
    @Autowired
    private RelationsMapper relationsMapper;
    @Autowired
    private AttendanceMapper attendanceMapper;

    @Override
    public List<StudentInfo> selectAllStudent() {
        return studentInfoMapper.selectAllStudent();
    }

    @Override
    public StudentInfo selectOneStudent(String uName, String uConnect) {
        return studentInfoMapper.selectOneStudent(uName, uConnect);
    }

    @Override
    public int insertStudentInfo(StudentInfo studentInfo) {
        StudentInfo s = studentInfoMapper.selectStudentByName_Connect(studentInfo.getuName(), studentInfo.getuConnect());
        if ( null == s ){
            return studentInfoMapper.insertStudentInfo(studentInfo);
        }else {
            return -1;
        }
    }

    @Override
    public int deleteOneStudent(int uId) {
        return studentInfoMapper.deleteOneStudent(uId);
    }

    @Override
    public int updateOneStudent(StudentInfo studentInfo){
        return studentInfoMapper.updateOneStudent(studentInfo);
    }

    @Override
    public int updateOneStudentFingerId(int uId, int uFingerId) {
        return studentInfoMapper.updateOneStudentFingerId(uId,uFingerId);
    }

    @Override
    public int updateOneStudentConnect(int uId, String uConnect) {
        return studentInfoMapper.updateOneStudentConnect(uId,uConnect);
    }

    @Override
    public int updateOneStudentComment(int uId, String comment) {
        return studentInfoMapper.updateOneStudentComment(uId,comment);
    }


    @Override
    public  StudentInfo selectStudentById(int uId){
        return studentInfoMapper.selectStudentById(uId);
    }

    @Override
    public StudentInfo selectStudentByName_Connect(String uName, String uConnect) {
        return studentInfoMapper.selectStudentByName_Connect(uName,uConnect);
    }

    @Override
    public List<StudentInfo> searchStudent(String keyword, int offset, int limit){
        return studentInfoMapper.searchStudent(keyword, offset, limit);
    }

    @Override
    public List<StudentInfo> selectPageStudent(int offset, int limit) {
        return studentInfoMapper.selectPageStudent(offset, limit);
    }

    @Override
    public int selectPageStudentCount(){
        return studentInfoMapper.selectPageStudentCount();
    }

    @Override
    public int searchStudentCount(String keyword){
        return studentInfoMapper.searchStudentCount(keyword);
    }

    @Override
    public List<StudentInfo> selectStudentInfoByCourseId(int cId) {
        return studentInfoMapper.selectStudentByCourseId(cId);
    }

    @Override
    public List<StudentInfo> selectStudentPageByCourseId(int cId, int offset, int limit) {
        return studentInfoMapper.selectStudentPageByCourseId(cId,offset,limit);
    }

    @Override
    public int searchStudentCountByCourse(int cId,String keyword) {
        return studentInfoMapper.searchStudentCountByCourse(cId,keyword);
    }

    @Override
    public int selectPageStudentCountByCourse(int cId) {
        return studentInfoMapper.selectPageStudentCountByCourse(cId);
    }

    @Override
    public List<StudentInfo> searchStudentByCourse(int cId, String keyword, int offset, int limit) {
        return studentInfoMapper.searchStudentByCourse(cId,keyword,offset,limit);
    }

    @Override
    public List<StudentInfo> queryStudent(String query, int limit) {
        return studentInfoMapper.QueryStudent(query,limit);
    }

    @Override
    public int insertCourseForStudent(int uId, int cId, int cNumberLast, Date overDate) {
        Relations r = relationsMapper.selectRelationBycId_uId(uId, cId);
        if( null == r ) {
            return relationsMapper.insertCourseForStudent(uId,cId, cNumberLast,overDate);
        }
        else {
            return -1;
        }

    }

    @Override
    public int deleteRelationInfoBycId_uId(int uId, int cId) {
        return relationsMapper.deleteRelationInfoBycId_uId(uId, cId);
    }

    @Override
    public List<relationTableSearchResultItem> orderStudentByCourseNumber(int cId, String sort, String order, int offset, int limit) {
        return studentInfoMapper.orderStudentByCourseNumber(cId, sort,order,offset,limit);
    }

    // ##############################################################################
    @Override
    public void sendMessage(String cName, String uConnect, String uName, String attTime, int cNumberLast, Date overDate){

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
            query+="mobile="+ uConnect + "&";
            if(cNumberLast < 50) {
                query += "content=【兰州斯渊艺术】您好，" + uName + "同学于" + attTime + "开始课程。剩余课程:" + cNumberLast + "节。(请合理安排时间)电话：4679789" + "&";
            }else {
                query += "content=【兰州斯渊艺术】您好，" + uName + "同学于" + attTime + "开始课程。课程截止日期为:" + overDate + "电话：4679789" + "&";
            }
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

    // ##############################################################################

    @Override
    public int insertAttendance(int uId, int cId, Timestamp arriveTime, Timestamp leaveTime, String attComment){
        return attendanceMapper.insertAttendance(uId, cId, arriveTime, leaveTime, attComment);
    }

    @Override
    public Attendance selectAttendanceInfo(int attId){
        return attendanceMapper.selectAttendanceInfo(attId);
    }

    @Override
    public List<Attendance> selectAttendanceByUid_Cid(int uId, int cId){
        return attendanceMapper.selectAttendanceByUid_Cid(uId,cId);
    }

    @Override
    public Attendance selectAttendanceByUid_AttTime(int uId, Timestamp arriveTime){
        return attendanceMapper.selectAttendanceByUid_AttTime(uId,arriveTime);
    }

    @Override
    public  List<Attendance> searchOneStudentAttendance(int uId, int cId,String keyword,int offset, int limit){
        return attendanceMapper.searchOneStudentAttendance(uId,cId,keyword,offset,limit);
    }

    @Override
    public  int searchAttendanceByArriveTime(int uId, int cId, String keyword){
        return attendanceMapper.searchAttendanceByArriveTime(uId, cId, keyword);
    }

    @Override
    public List<Attendance> selectAttendancePageByUid_Cid(int uId, int cId, int offset,int limit){
        return attendanceMapper.selectAttendancePageByUid_Cid(uId, cId, offset, limit);
    }

    @Override
    public int selectPageAttendanceCount(int uId, int cId){
        return attendanceMapper.selectPageAttendanceCount(uId, cId);
    }

    @Override
    public int deleteOneRecord(int attId){
        return attendanceMapper.deleteOneRecord(attId);
    }
}
