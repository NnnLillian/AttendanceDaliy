package com.example.demo.mapper;

import com.example.demo.entity.Attendance;
import org.apache.ibatis.annotations.*;
import java.sql.Timestamp;
import java.util.List;

public interface AttendanceMapper {

    @Select("SELECT * FROM attendanceinfo WHERE attId = #{attId};")
    Attendance selectAttendanceInfo(@Param("attId") int attId);

    @Select("SELECT * FROM attendanceinfo WHERE arriveTime between #{start} AND #{end};")
    List<Attendance> selectAttendanceRecord(@Param("start") Timestamp startTime, @Param("end") Timestamp endTime, @Param("uId") int uId);

    @Insert("INSERT INTO attendanceinfo(uId, cId, arriveTime, leaveTime, attComment) VALUES (#{uId}, #{cId},#{arriveTime},#{leaveTime},#{attComment});")
    int insertAttendance(@Param("uId") int uId, @Param("cId") int cId, @Param("arriveTime") Timestamp arriveTime, @Param("leaveTime") Timestamp leaveTime, @Param("attComment") String attComment);

    @Select("SELECT * From attendanceinfo where uId = #{uId} and cId = #{cId};")
    List<Attendance> selectAttendanceByUid_Cid(@Param("uId") int uId, @Param("cId") int cId);

    @Select("SELECT * From attendanceinfo where uId = #{uId} and arriveTime = #{arriveTime};")
    Attendance selectAttendanceByUid_AttTime(@Param("uId") int uId, @Param("arriveTime") Timestamp arriveTime);

    @Select("SELECT * FROM attendanceinfo WHERE uId = #{uId} and cId = #{cId} and attendanceinfo.arriveTime like #{keyword} LIMIT #{limit}, #{count};")
    List<Attendance> searchOneStudentAttendance(@Param("uId") int uId, @Param("cId") int cId, @Param("Keyword") String keyword, @Param("limit") int offset, @Param("count") int limit);

    @Select("SELECT COUNT(*) From attendanceinfo where uId = #{uId} and cId = #{cId} and attendanceinfo.arriveTime like #{keyword};")
    int searchAttendanceByArriveTime(@Param("uId") int uId, @Param("cId") int cId, @Param("keyword") String keyword);

    @Select("SELECT * FROM attendanceinfo WHERE uId = #{uId} and cId = #{cId} LIMIT #{limit}, #{count};")
    List<Attendance> selectAttendancePageByUid_Cid(@Param("uId") int uId, @Param("cId") int cId, @Param("limit") int offset, @Param("count") int limit);

    @Select("SELECT COUNT(*) FROM attendanceinfo WHERE uId = #{uId} and cId = #{cId};")
    int selectPageAttendanceCount(@Param("uId") int uId, @Param("cId") int cId);

    @Delete("DELETE FROM attendanceinfo WHERE attId = #{attId};")
    int deleteOneRecord(@Param("attId") int attId);
}
