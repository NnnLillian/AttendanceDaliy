package com.example.demo.mapper;

import com.example.demo.entity.CourseInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface CourseInfoMapper {

    List<CourseInfo> selectCourseAll();
    List<CourseInfo> selectCourseInfoBySortId(int cSortId);
//    CourseInfo selectBycSortId(int cId);
    List<CourseInfo> selectBycName(String cName);
    List<CourseInfo> selectBycImgUrl(String cImgUrl);

//    CourseInfo selectOne( @Param("cId") int cId,@Param("cName") String cName);

    int insert(@Param("cSortId") int cSortId, @Param("cName") String cName, @Param("cImgUrl") String cImgUrl);

    @Delete("DELETE FROM courseinfo WHERE cId = #{cId};")
    int deleteCourse(@Param("cId") int cId);

    @Update("UPDATE courseinfo SET cImgUrl = #{cImgUrl} WHERE cName = #{cName};")
    int updateCourseImage(@Param("cImgUrl") int cImgUrl, @Param("cName") int cName);

    @Select("SELECT * FROM courseinfo WHERE cId = #{cId}")
    CourseInfo selectCourseInfoById(@Param("cId") int cId);

    @Select("SELECT * FROM courseinfo WHERE cName = #{cName}")
    CourseInfo selectCourseInfoByName(@Param("cName") String cName);

    @Select("SELECT cId FROM courseinfo WHERE cName = #{cName}")
    int selectCourseIdInfoByName(@Param("cName") String cName);

    @Select("SELECT * From courseinfo,(select * from srelationc where uId = #{uId})t where courseinfo.cId= t.cId;")
    List<CourseInfo> selectOneStudentCourseInfos(@Param("uId") int uId);

    @Select("select * from courseinfo,(SELECT * from srelationc WHERE uId = #{uId}) t WHERE courseinfo.cId= t.cId and courseinfo.cName like #{keyword} LIMIT #{limit}, #{count}")
    List<CourseInfo> searchOneStudentCourse(@Param("uId") int uId, @Param("keyword") String keyword, @Param("limit") int offset, @Param("count") int limit);

    @Select("select COUNT(*) from courseinfo,(SELECT cId from srelationc WHERE uId = #{uId}) t WHERE courseinfo.cId= t.cId and courseinfo.cName like #{keyword};")
    int searchCourseCountByuId(@Param("uId")int uId, @Param("keyword")String keyword);

    @Select("SELECT * FROM courseinfo,(SELECT cId from srelationc WHERE uId = #{uId}) t WHERE courseinfo.cId= t.cId LIMIT #{limit}, #{count};")
    List<CourseInfo> selectCoursePageByStudentId(@Param("uId") int uId,@Param("limit") int offset, @Param("count") int limit);

    @Select("SELECT COUNT(*) AS count FROM courseinfo,(SELECT cId from srelationc WHERE uId = #{uId}) t WHERE courseinfo.cId= t.cId;")
    int selectPageCourseCountByuId(@Param("uId") int uId);

    @Select("SELECT cName FROM courseinfo WHERE cName LIKE #{query} LIMIT #{limit};")
    List<String> QueryCourse(@Param("query") String query, @Param("limit") int limit);


}
