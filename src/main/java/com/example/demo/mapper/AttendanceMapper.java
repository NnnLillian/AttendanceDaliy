package com.example.demo.mapper;

import com.example.demo.entity.Attendance;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

public interface AttendanceMapper {

    @Select("SELECT * FROM attendanceinfo WHERE attId = #{attId};")
    Attendance selectAttendanceInfo(int attId);

    @Delete("DELETE FROM attendanceinfo WHERE attId = #{attId};")
    Attendance deleteAttendanceInfo(int attId);

    @Select("SELECT * FROM attendanceinfo WHERE arriveTime between #{start} AND #{end};")
    Attendance selectAttendanceRecord(@Param("start") Timestamp startTime, @Param("end") Timestamp endTime, @Param("uId") int uId);

    @Insert("INSERT INTO attendanceinfo(uId, cId, arriveTime, leaveTime, attComment) VALUES (#{uId}, #{cId},#{arriveTime},#{leaveTime},#{attComment});")
    int insertAttendance(@Param("uId") int uId, @Param("cId") int cId, @Param("arriveTime") Timestamp arriveTime, @Param("leaveTime") Timestamp leaveTime, @Param("attComment") String attComment);

    @Select("SELECT * From attendanceinfo where uId = #{uId} and cId = #{cId};")
    List<Attendance> selectAttendanceByUid_Cid(@Param("uId") int uId, @Param("cId") int cId);

    @Select("SELECT * From attendanceinfo where uId = #{uId} and arriveTime = #{arriveTime};")
    Attendance selectAttendanceByUid_AttTime(@Param("uId") int uId, @Param("arriveTime") Timestamp arriveTime);

}
