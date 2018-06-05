package com.example.demo.mapper;

import com.example.demo.entity.Attendance;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.sql.Time;
import java.sql.Timestamp;

public interface AttendanceMapper {

    @Select("SELECT * FROM attendanceinfo WHERE attId = #{attId};")
    Attendance selectAttendanceInfo(int attId);

    @Delete("DELETE FROM attendanceinfo WHERE attId = #{attId};")
    Attendance deleteAttendanceInfo(int attId);

    @Select("SELECT * FROM attendanceinfo WHERE arriveTime between #{start} AND #{end};")
    Attendance selectAttendanceRecord(@Param("start") Timestamp startTime, @Param("end") Timestamp endTime, @Param("uId") int uId);

}
