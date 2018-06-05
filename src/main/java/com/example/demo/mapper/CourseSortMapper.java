package com.example.demo.mapper;

import com.example.demo.entity.CourseSort;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CourseSortMapper {

    @Select("SELECT * FROM coursesortinfo;")
    List<CourseSort> selectAll();

    @Select("SELECT * FROM coursesortinfo WHERE cSortId = #{cSortId};")
    CourseSort selectByCourseId(int cSortId);

    @Insert("INSERT INTO coursesortinfo(cSortName) VALUES (#{cSortName})")
    int insertCourseInfo(@Param("cSortName") String cSortName);

    @Select("SELECT * FROM coursesortinfo WHERE cSortName = #{cSortName}")
    CourseSort selectCourseSortInfoByName(@Param("cSortName") String cSortName);

    @Select("SELECT cSortId FROM coursesortinfo WHERE cSortName = #{cSortName}")
    int selectCourseSortIdInfoByName(@Param("cSortName") String cSortName);

    @Delete("DELETE FROM coursesortinfo WHERE cSortId = #{cSortId};")
    int deleteCourseSort(@Param("cSortId") int cSortId);
}
