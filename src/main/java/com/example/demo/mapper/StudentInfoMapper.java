package com.example.demo.mapper;

import com.example.demo.entity.StudentInfo;
import com.example.demo.entity.relationTableSearchResultItem;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface StudentInfoMapper {
    List<StudentInfo> selectAllStudent();

    StudentInfo selectOneStudent(@Param("uName") String uName, @Param("uConnect") String uConnect);

    int insertStudentInfo(StudentInfo studentInfo);

    int deleteOneStudent(@Param("uId") int uId);

    int updateOneStudent(StudentInfo studentInfo);

    int updateOneStudentFingerId(@Param("uId") int uId, @Param("uFingerId") int uFingerId);

    int updateOneStudentConnect(@Param("uId") int uId, @Param("uConnect") String uConnect);

    int updateOneStudentComment(@Param("uId") int uId, @Param("comment") String comment);

    StudentInfo selectStudentById(@Param("uId") int uId);

    List<StudentInfo> searchStudent(@Param("keyword") String keyword, @Param("limit") int offset, @Param("count") int limit);

    List<relationTableSearchResultItem> orderStudentByCourseNumber(@Param("cId") int cId, @Param("sort") String sort, @Param("order") String order, @Param("offset") int offset, @Param("limit") int limit);

    @Select("SELECT * FROM studentinfo WHERE uName = #{uName} AND uConnect = #{uConnect};")
    StudentInfo selectStudentByName_Connect(@Param("uName") String uName, @Param("uConnect") String uConnect);

    @Select("SELECT * FROM studentinfo LIMIT #{limit}, #{count};")
    List<StudentInfo> selectPageStudent(@Param("limit") int offset, @Param("count") int limit);

    @Select("select COUNT(*) from studentinfo where concat(uId,uName,uConnect) like #{keyword};")
    int searchStudentCount(@Param("keyword")String keyword);

    @Select("SELECT COUNT(*) AS count FROM studentinfo;")
    int selectPageStudentCount();

    @Select("select * from studentinfo,(SELECT uId from srelationc WHERE cId = #{cId}) t WHERE studentinfo.uId = t.uId and concat(studentinfo.uId,studentinfo.uName,studentinfo.uConnect) like #{keyword} LIMIT #{limit}, #{count}")
    List<StudentInfo> searchStudentByCourse(@Param("cId") int cId, @Param("keyword") String keyword, @Param("limit") int offset, @Param("count") int limit);

    @Select("SELECT * FROM studentinfo,(SELECT uId from srelationc WHERE cId = #{cId}) t WHERE studentinfo.uId = t.uId;")
    List<StudentInfo> selectStudentByCourseId(@Param("cId") int cId);

    @Select("SELECT * FROM studentinfo,(SELECT uId from srelationc WHERE cId = #{cId}) t WHERE studentinfo.uId = t.uId LIMIT #{limit}, #{count};")
    List<StudentInfo> selectStudentPageByCourseId(@Param("cId") int cId,@Param("limit") int offset, @Param("count") int limit);

    @Select("select COUNT(*) from studentinfo,(SELECT uId from srelationc WHERE cId = #{cId}) t WHERE studentinfo.uId = t.uId and concat(studentinfo.uId,studentinfo.uName,studentinfo.uConnect) like #{keyword};")
    int searchStudentCountByCourse(@Param("cId")int cId,@Param("keyword")String keyword);

    @Select("SELECT COUNT(*) AS count FROM studentinfo,(SELECT uId from srelationc WHERE cId = #{cId}) t WHERE studentinfo.uId = t.uId;")
    int selectPageStudentCountByCourse(@Param("cId") int cId);

    @Select("SELECT * FROM studentinfo WHERE uName LIKE #{query} LIMIT #{limit};")
    List<StudentInfo> QueryStudent(@Param("query") String query, @Param("limit") int limit);

}
