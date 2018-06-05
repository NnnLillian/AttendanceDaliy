package com.example.demo.service;

import com.example.demo.entity.CourseInfo;
import com.example.demo.entity.CourseSort;
import com.example.demo.entity.Relations;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

public interface CourseService {

    List<CourseInfo> selectCourseAll();

    List<CourseInfo> selectAllCourseInfo();
    List<CourseInfo> selectCourseInfoBySortId(int cSortId);
    CourseInfo selectCourseInfoById(int cId);

    List<CourseSort> selectAllCourseSort();
    CourseSort selectCourseSortById(int cSortId);

    int insert (int cSortId, String cName, String cImgUrl);

    int deleteCourse(int cId);

    List<CourseSort> selectAll();

    int selectCourseIdInfoByName(String cName);

    CourseSort selectCourseSortInfoByName(String cSortName);

    int selectCourseSortIdInfoByName(String cSortName);

    int insertCourseSort(String cSortName);

    List<CourseInfo> selectOneStudentCourseInfos(int uId);

    List<CourseInfo> searchOneStudentCourse(int uId,String keyword, int offset, int limit);

    int searchCourseCountByuId(int uId, String keyword);

    List<CourseInfo> selectCoursePageByStudentId( int uId,int offset, int limit);

    int selectPageCourseCountByuId(int uId);

    //关于表 srelationc
    List<Relations> selectRelationByCid(int cId);

    Relations selectRelationBycId_uId(int uId, int cId);

    List<Relations> selectRelationByUid(int uId);

    int updateCourseNumber(int id, int cNumberLast);

    int updateCoursedNumber(int id, int cNumberEd);

    int updateCourseOverDate(int id, Date overDate);

    List<String> queryCourse(String query, int limit);

    int updateCourseImage(int cImgUrl, int cName);

    int deleteCourseSort(int cSortId);



}
