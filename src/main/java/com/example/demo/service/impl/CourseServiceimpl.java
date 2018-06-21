package com.example.demo.service.impl;

import com.example.demo.entity.CourseInfo;
import com.example.demo.entity.CourseSort;
import com.example.demo.entity.Relations;
import com.example.demo.mapper.CourseInfoMapper;
import com.example.demo.mapper.CourseSortMapper;
import com.example.demo.mapper.RelationsMapper;
import com.example.demo.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CourseServiceimpl implements CourseService {
    @Autowired
    private CourseInfoMapper courseInfoMapper;

    @Autowired
    private CourseSortMapper courseSortMapper;

    @Autowired
    private RelationsMapper relationsMapper;

    @Override
    public  List<CourseInfo> selectCourseAll(){return  courseInfoMapper.selectCourseAll();}


    @Override
    public List<CourseInfo> selectAllCourseInfo() {
        return courseInfoMapper.selectCourseAll();
    }


    @Override
    public List<CourseInfo> selectCourseInfoBySortId(int cSortId){
        return courseInfoMapper.selectCourseInfoBySortId(cSortId);
    }

    @Override
    public CourseInfo selectCourseInfoById(int cId) {
        return courseInfoMapper.selectCourseInfoById(cId);
    }

    @Override
    public List<CourseSort> selectAllCourseSort() {
        return courseSortMapper.selectAll();
    }

    @Override
    public CourseSort selectCourseSortById(int cSortId) {
        return courseSortMapper.selectByCourseId(cSortId);
    }


    @Override
    public int insert(int cSortId, String cName, String cImgUrl) {
        CourseInfo c = courseInfoMapper.selectCourseInfoByName(cName);
        if (null == c){
          return courseInfoMapper.insert(cSortId,cName,cImgUrl);
        }
        else {
            return -1;
        }
    }

    @Override
    public int deleteCourse(int cId) {
        return courseInfoMapper.deleteCourse(cId);
    }

    @Override
    public List<CourseSort> selectAll() {
        return courseSortMapper.selectAll();
    }

    @Override
    public int selectCourseIdInfoByName(String cName) {
        return courseInfoMapper.selectCourseIdInfoByName(cName);
    }

    @Override
    public CourseSort selectCourseSortInfoByName(String cSortName) {
        return courseSortMapper.selectCourseSortInfoByName(cSortName);
    }

    @Override
    public int selectCourseSortIdInfoByName(String cSortName) {
        return courseSortMapper.selectCourseSortIdInfoByName(cSortName);
    }

    @Override
    public int insertCourseSort(String cSortName) {
        CourseSort cs = courseSortMapper. selectCourseSortInfoByName(cSortName);
        if (null == cs){
            return courseSortMapper.insertCourseInfo(cSortName);
        }
        else {
            return -1;
        }
    }


    @Override
    public List<Relations> selectRelationByCid(int cId){
        return relationsMapper.selectRelationByCid(cId);
    }

    @Override
    public Relations selectRelationBycId_uId(int uId, int cId) {
        return relationsMapper.selectRelationBycId_uId(uId,cId);
    }

    @Override
    public List<Relations> selectRelationByUid(int uId) {
        return relationsMapper.selectRelationByUid(uId);
    }

    @Override
    public int updateCourseNumber(int id, int cNumberLast) {
        return relationsMapper.updateCourseNumber(id, cNumberLast);
    }

    @Override
    public int updateCoursedNumber(int id, int cNumberEd) {
        return relationsMapper.updateCoursedNumber(id, cNumberEd);
    }

    @Override
    public int updateCourseOverDate(int id, Date overDate) {
        return relationsMapper.updateCourseOverDate(id, overDate);
    }

    @Override
    public List<String> queryCourse(String query, int limit) {
        return courseInfoMapper.QueryCourse(query,limit);
    }

    @Override
    public int updateCourseImage(int cImgUrl, int cName) {
        return courseInfoMapper.updateCourseImage(cImgUrl, cName);
    }

    @Override
    public int deleteCourseSort(int cSortId) {
        return courseSortMapper.deleteCourseSort(cSortId);
    }


    @Override
    public List<CourseInfo> selectOneStudentCourseInfos(int uId) {
        return courseInfoMapper.selectOneStudentCourseInfos(uId);
    }

    @Override
    public List<CourseInfo> searchOneStudentCourse(int uId, String keyword, int offset,int limit) {
        return courseInfoMapper.searchOneStudentCourse(uId, keyword,offset, limit);
    }

    @Override
    public int searchCourseCountByuId(int uId, String keyword) {
        return courseInfoMapper.searchCourseCountByuId(uId,keyword);
    }

    @Override
    public List<CourseInfo> selectCoursePageByStudentId(int uId, int offset, int limit) {
        return courseInfoMapper.selectCoursePageByStudentId(uId,offset,limit);
    }

    @Override
    public int selectPageCourseCountByuId(int uId) {
        return courseInfoMapper.selectPageCourseCountByuId(uId);
    }


}
