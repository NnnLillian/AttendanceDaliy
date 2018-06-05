package com.example.demo.service.impl;

import com.example.demo.entity.Relations;
import com.example.demo.entity.StudentInfo;
import com.example.demo.entity.relationTableSearchResultItem;
import com.example.demo.mapper.RelationsMapper;
import com.example.demo.mapper.StudentInfoMapper;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class StudentServiceimpl implements StudentService{
    @Autowired
    private StudentInfoMapper studentInfoMapper;
    @Autowired
    private RelationsMapper relationsMapper;

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


}
