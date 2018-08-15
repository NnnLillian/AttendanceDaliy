package com.example.demo.service;

import com.example.demo.entity.Attendance;
import com.example.demo.entity.Relations;
import com.example.demo.entity.StudentInfo;
import com.example.demo.entity.relationTableSearchResultItem;


import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public interface StudentService {

    List<StudentInfo> selectAllStudent();

    StudentInfo selectOneStudent(String uName, String uConnect);

    int insertStudentInfo (StudentInfo studentInfo);

    int deleteOneStudent(int uId);
    int updateOneStudent(StudentInfo studentInfo);
    int updateOneStudentFingerId(int uId, int uFingerId);
    int updateOneStudentConnect(int uId, String uConnect);
    int updateOneStudentComment(int uId, String comment);


    StudentInfo selectStudentById(int uId);
    StudentInfo selectStudentByName_Connect(String uName, String uConnect);

    List<StudentInfo> searchStudent(String keyword,int offset, int limit);

    List<StudentInfo> selectPageStudent(int offset, int limit) ;

    int selectPageStudentCount() ;

    int searchStudentCount(String keyword);

    List<StudentInfo> selectStudentInfoByCourseId(int cId);

    List<StudentInfo> selectStudentPageByCourseId(int cId, int offset, int limit);

    int searchStudentCountByCourse(int cId,String keyword);

    int selectPageStudentCountByCourse(int cId);

    List<StudentInfo> searchStudentByCourse(int cId,String keyword, int offset, int limit);
    List<StudentInfo> queryStudent(String query, int limit);

    int insertCourseForStudent(int uId, int cId, int cNumberLast, Date overDate);
    int deleteRelationInfoBycId_uId(int uId, int cId);

    List<relationTableSearchResultItem> orderStudentByCourseNumber(int cId, String sort, String order, int offset, int limit);

    void sendMessage(String cName, String uConnect, String uName, String attTime, int cNumberLast);




    // AttendanceMapper
    int insertAttendance(int uId, int cId, Timestamp arriveTime, Timestamp leaveTime, String attComment);

    Attendance selectAttendanceInfo(int attId);

    List<Attendance> selectAttendanceByUid_Cid(int uId, int cId);

    Attendance selectAttendanceByUid_AttTime(int uId,Timestamp arriveTime);

    List<Attendance> searchOneStudentAttendance(int uId, int cId,String keyword,int offset, int limit);

    int searchAttendanceByArriveTime(int uId, int cId, String keyword);

    List<Attendance> selectAttendancePageByUid_Cid(int uId, int cId, int offset,int limit);

    int selectPageAttendanceCount(int uId, int cId);

    int deleteOneRecord(int attId);

//    ######################################################################################
}
