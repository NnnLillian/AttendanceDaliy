package com.example.demo.mapper;

import com.example.demo.entity.Relations;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

public interface RelationsMapper {

    @Select("SELECT * FROM srelationc WHERE id = #{id};")
    Relations selectRelationInfo(int id);

    @Select("SELECT * FROM srelationc WHERE uId = #{uId};")
    List<Relations> selectRelationByUid(int uId);

    @Select("SELECT * FROM srelationc WHERE cId = #{cId};")
    List<Relations> selectRelationByCid(int cId);

    @Select("SELECT * FROM srelationc WHERE uId = #{uId} AND cId = #{cId};")
    Relations selectRelationBycId_uId(@Param("uId") int uId, @Param("cId") int cId);

    @Select("SELECT * FROM srelationc WHERE cNumberLast < 4")
    List<Relations> selectStudentIdWithLittleCourse();

    @Delete("DELETE FROM srelationc WHERE uId = #{uId} AND cId = #{cId};")
    int deleteRelationInfoBycId_uId(@Param("uId") int uId, @Param("cId") int cId);

    @Update("UPDATE srelationc SET cNumberLast = #{cNumberLast} WHERE id = #{id};")
    int updateCourseNumber(@Param("id") int id, @Param("cNumberLast") int cNumberLast);

    @Update("UPDATE srelationc SET cNumberEd = #{cNumberEd} WHERE id = #{id};")
    int updateCoursedNumber(@Param("id") int id, @Param("cNumberEd") int cNumberEd);

    @Update("UPDATE srelationc SET overDate = #{overDate} WHERE id = #{id};")
    int updateCourseOverDate(@Param("id") int id, @Param("overDate") Date overDate);

    @Insert("INSERT INTO srelationc(uId, cId, cNumberLast, overDate) VALUES (#{uId}, #{cId},#{cNumberLast},#{overDate}); ")
    int insertCourseForStudent(@Param("uId")int uId, @Param("cId") int cId,@Param("cNumberLast") int cNumberLast, @Param("overDate") Date overDate);

    @Update("UPDATE srelationc SET cNumberEd = #{cNumberEd}, cNumberLast = #{cNumberLast} WHERE id = #{id}")
    int updateRelation(Relations relations);
}
