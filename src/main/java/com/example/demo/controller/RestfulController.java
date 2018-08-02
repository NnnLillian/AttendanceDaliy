package com.example.demo.controller;

import com.example.demo.entity.*;
import com.example.demo.mapper.RelationsMapper;
import com.example.demo.service.CourseService;
import com.example.demo.service.StudentService;
import com.fasterxml.jackson.databind.util.JSONPObject;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.annotation.Resource;
import java.io.*;

import java.net.URL;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/rest-api")
public class RestfulController {

    @Autowired
    private CourseService courseService;
    @Autowired
    private StudentService studentService;


    //    得到该学校所有学生列表
    @GetMapping("/allStudents")

    public studentTableSearchResult getAllStudent(@RequestParam("offset") int offset,
                                                  @RequestParam("limit") int limit,
                                                  @RequestParam("order") String order,
                                                  @RequestParam("search") String search) {

        studentTableSearchResult result = new studentTableSearchResult();
        List<StudentInfo> studentInfos;
        if (limit > 50) {
            limit = 50;
        }
        if (search != null && search.length() != 0) {
            search = "%" + search + "%";
            studentInfos = studentService.searchStudent(search, offset, limit);
            int total = studentService.searchStudentCount(search);
            result.setTotal(total);
            result.setRows(studentInfos);

        } else {
            studentInfos = studentService.selectPageStudent(offset, limit);
            int total = studentService.selectPageStudentCount();
            result.setTotal(total);
            result.setRows(studentInfos);
//            studentInfos = studentService.selectAllStudent();
        }

        return result;
    }

    //    所有学生界面——删除学生
    @RequestMapping(value = "/removeStudent/{uId}", method = RequestMethod.DELETE)
    public int deleteStudent(@PathVariable(value = "uId") int uId) throws AssertionError {

        List<Relations> r = courseService.selectRelationByUid(uId);

        if (r != null) {
            return studentService.deleteOneStudent(uId);
        }
        else {
            throw new AssertionError("剩余课程不为空，无法删除！");
        }
    }

    //    课程信息页面——得到同一门课程下的所有学生
    @GetMapping("/allStudentsByCourse/{cId}")
    public relationTableSearchResult getAllStudentByCourse(
            @PathVariable("cId") Integer cId,
            @RequestParam("offset") int offset,
            @RequestParam("limit") int limit,
            @RequestParam("order") String order,
            @RequestParam(value = "sort", required = false) String sort,
            @RequestParam("search") String search) {

        relationTableSearchResult result = new relationTableSearchResult();
        List<StudentInfo> studentInfos;
        int total;

        if (limit > 50) {
            limit = 50;
        }
        if (search == null || search.trim().length() == 0) {

            total = studentService.selectPageStudentCountByCourse(cId);
//          studentInfos = studentService.selectAllStudent();

            if (! (sort == null || sort.trim().length() == 0)) {
                result.setRows1(studentService.orderStudentByCourseNumber(cId, sort, order, offset, limit));

            }
            else {
                studentInfos = studentService.selectStudentPageByCourseId(cId, offset, limit);
//            studentInfos = studentService.orderStudentByCourseNumber(sort,order,offset,limit);
                result.setRows(studentInfos);
            }

            result.setTotal(total);

        } else {

            search = "%" + search + "%";
            studentInfos = studentService.searchStudentByCourse(cId, search, offset, limit);
            total = studentService.searchStudentCountByCourse(cId, search);
            result.setTotal(total);
            result.setRows(studentInfos);

        }

        for (relationTableSearchResultItem it : result.getRows()) {

            Relations r = courseService.selectRelationBycId_uId(it.getuId(), cId);
            it.setcNumberEd(r.getcNumberEd());
            it.setcNumberLast(r.getcNumberLast());
            if (null != r.getOverDate()) {
                it.setOverDate(r.getOverDate());
            }
        }


        return result;
    }

    //  课程信息页面——增加学生
    @RequestMapping(value = "/addNewStudentForOneCourse/{cId}", method = RequestMethod.PUT)
    public @ResponseBody
    int addOneRelationForCourse(@PathVariable("cId") int cId,
                                @RequestBody Relations relations) throws Exception {
        int uId = relations.getuId();
        int cNumberLast = relations.getcNumberLast();
        Date overDate = relations.getOverDate();
        System.out.println(cId);
        System.out.println(uId);
        System.out.println(cNumberLast);
        System.out.println(overDate);
        int ret = studentService.insertCourseForStudent(uId, cId, cNumberLast, overDate);
        if (-1 == ret) {
            throw new Exception("学生已存在");
        }
        return 0;
    }
    //    课程信息页面——删除学生
    @RequestMapping(value = "deleteStudent/{uId}/{cId}", method = RequestMethod.DELETE)
    public int deleteStudentFromOneCourse(@PathVariable(value = "uId") int uId,
                                      @PathVariable(value = "cId") int cId) throws AssertionError {

        Relations r = courseService.selectRelationBycId_uId(uId, cId);

        if (((r != null) && (r.getcNumberLast() == 0))) {
            return studentService.deleteRelationInfoBycId_uId(uId, cId);
        }
        else {
            throw new AssertionError("剩余课程不为0，无法删除！");
        }
    }


    //    学生个人页面--个人信息部分
    @GetMapping("/studentInfo/{uId}")
    public StudentInfo getStudentInfo(@PathVariable int uId) {
        return studentService.selectStudentById(uId);
    }

    @PostMapping("/studentInfo/{uId}")
    public int setStudentInfoById(@PathVariable("uId") int uId, @RequestParam("name") String name,
                                  @RequestParam("pk") String pk, @RequestParam("value") String value)
            throws NotImplementedException {
        if (name.equals("uFingerId")) {
            int uFingerId = Integer.parseInt(value);
            return studentService.updateOneStudentFingerId(uId, uFingerId);
        } else if (name.equals("uConnect")) {
            String uConnect = value;
            return studentService.updateOneStudentConnect(uId, uConnect);
        } else if (name.equals("comment")) {
            String comment = value;
            return studentService.updateOneStudentComment(uId, comment);
        } else {
            throw new NotImplementedException();
        }

    }
//    public int ModifyStudent(StudentInfo studentInfo) {
//        studentService.updateOneStudent(studentInfo);
//        return 0;
//    }


    //    学生个人页面--课程部分--修改已有课程信息
    @GetMapping("/OneStudentByCourse/{uId}")
    public StudentRelationTableSearchResult getOneStudentByCourse(
            @PathVariable("uId") Integer uId,
            @RequestParam("offset") int offset,
            @RequestParam("limit") int limit,
            @RequestParam("order") String order,
            @RequestParam("search") String search) {

        StudentRelationTableSearchResult result = new StudentRelationTableSearchResult();

        List<CourseInfo> courseInfos;

        if (limit > 50) {
            limit = 50;
        }
        if (search != null && search.length() != 0) {
            search = "%" + search + "%";
            courseInfos = courseService.searchOneStudentCourse(uId, search, offset, limit);
            int total = courseService.searchCourseCountByuId(uId, search);
            result.setTotal(total);
            result.setRows(courseInfos);

        } else {
            courseInfos = courseService.selectCoursePageByStudentId(uId, offset, limit);
            int total = courseService.selectPageCourseCountByuId(uId);
            result.setTotal(total);
            result.setRows(courseInfos);
//            studentInfos = studentService.selectAllStudent();
        }

        for (StudentRelationTableSearchResultItem it : result.getRows()) {

            Relations r = courseService.selectRelationBycId_uId(uId, it.getcId());
            it.setcNumberEd(r.getcNumberEd());
            it.setcNumberLast(r.getcNumberLast());
            it.setOverDate(r.getOverDate());
        }

        return result;
    }

    @PostMapping("/EditStudentCourse/{uId}")
    public int setStudentCourseInfoByUid(@PathVariable("uId") int uId,
                                         @RequestParam("cId") int cId,
                                         @RequestParam("name") String name,
                                         @RequestParam("value") String value
    ) throws ParseException {
        Relations relation = courseService.selectRelationBycId_uId(uId, cId);
        int id = relation.getId();
        if (name.equals("cNumberEd")) {
            int cNumberEd = Integer.parseInt(value);
            return courseService.updateCoursedNumber(id, cNumberEd);
        } else if (name.equals("cNumberLast")) {
            int cNumberLast = Integer.parseInt(value);
            return courseService.updateCourseNumber(id, cNumberLast);
        } else if (name.equals("overDate")) {
            System.out.println(value);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date overDate = sdf.parse(value);
            return courseService.updateCourseOverDate(id, overDate);
        } else {
            throw new NotImplementedException();
        }

    }

    //    学生个人页面--课程部分--增加课程信息
    @RequestMapping(value = "/addNewCourse/{uId}", method = RequestMethod.PUT)
    public @ResponseBody
    int addOneRelation(@PathVariable("uId") int uId,
                       @RequestBody Relations relations) throws Exception {
        String cName = relations.getcName();
        int cId = courseService.selectCourseIdInfoByName(cName);
        int cNumberLast = relations.getcNumberLast();
        Date overDate = relations.getOverDate();
        System.out.println(cId + cName + cNumberLast + overDate);
        int ret = studentService.insertCourseForStudent(uId, cId, cNumberLast, overDate);
        if (-1 == ret) {
            throw new Exception("课程已存在");
        }
        return 0;
    }

    //   学生个人页面--课程部分--删除课程信息
    @RequestMapping(value = "deleteCourse/{uId}/{cId}", method = RequestMethod.DELETE)
    public int deleteCourseForStudent(@PathVariable(value = "uId") int uId,
                                      @PathVariable(value = "cId") int cId)
            throws Exception {
        Relations r = courseService.selectRelationBycId_uId(uId, cId);
        if ((r == null) || r.getcNumberLast() != 0) {
            throw new Exception("不允许删除");
        } else {
            return studentService.deleteRelationInfoBycId_uId(uId, cId);
        }
    }

    //    自动补全课程名
    @GetMapping("/QueryCourse")
    public List<String> QueryCourse(@RequestParam("query") String query,
                                    @RequestParam("limit") int limit) {
        query = "%" + query + "%";
        return courseService.queryCourse(query, limit);
    }

    //    自动补全姓名
    @GetMapping("/QueryStudent")
    public QueryStudentResult QueryStudent(@RequestParam("query") String query,
                                           @RequestParam("limit") int limit) {
        query = "%" + query + "%";
        QueryStudentResult result = new QueryStudentResult();

        List<StudentInfo> students = studentService.queryStudent(query, limit);

        for (StudentInfo s : students) {
            s.setuFingerId(-1);
            s.setComment("");
        }

        result.setRetcode(0);
        result.setItems(students);

        return result;
    }
    //    增加课程类别
    @RequestMapping(value = "/newCourseSort", method = RequestMethod.PUT)
    public @ResponseBody
    int addOneCourseSort(@RequestBody CourseSort courseSort) throws Exception {
        String cSortName = courseSort.getcSortName();
        System.out.println(cSortName);
        int ret = courseService.insertCourseSort(cSortName);
        if (-1 == ret) {
            throw new Exception("课程类别已存在");
        }
        return 0;
    }

    //    增加课程信息
    @RequestMapping(value = "/newCourse", method = RequestMethod.PUT)
    public @ResponseBody
    int addOneCourse(@RequestBody CourseInfo courseInfo) throws Exception {
        String cImgUrl = courseInfo.getcImgUrl();
        String cSortName = courseInfo.getcSortName();
        int cSortId = courseService.selectCourseSortIdInfoByName(cSortName);
        String cName = courseInfo.getcName();
        System.out.println(cName + cSortId + cSortName);
        int ret = courseService.insert(cSortId, cName, cImgUrl);
        if (-1 == ret) {
            throw new Exception("课程已存在");
        }
        return 0;
    }
    //  增加课程图片
    @RequestMapping(value = "/imgUpload", method = RequestMethod.POST)
    public String uploadImage(@RequestParam("image")MultipartFile file) {

        String fname=file.getOriginalFilename();
        String postfix=fname.substring(fname.lastIndexOf('.'));
        fname += System.currentTimeMillis();
        fname = "" + fname.hashCode() + postfix;

        try {
            URL classPath = Thread.currentThread().getContextClassLoader().getResource("imgs/");
            String fPath = classPath.getPath() + fname;
            File f =  new File(fPath);

//            File f =  new File("./imgs/"+fname);
            FileOutputStream fos = new FileOutputStream(f);
            fos.write(file.getBytes());
            fos.close();

//          将字符串拼接称为JSON格式（但并不是JSON），返回前台，让前台解析
            return "{\"name\":\"/imgs/"+fname+"\"}";
        }catch (IOException e) {
            return "-1";
        }
    }

    //    删除课程类别
    @RequestMapping(value = "/deleteCourseSort/{cSortName}", method = RequestMethod.DELETE)
    public int deleteCourseSort(@PathVariable(value = "cSortName") String cSortName) throws AssertionError {

        int cSortId = courseService.selectCourseSortIdInfoByName(cSortName);
        List<CourseInfo> c = courseService.selectCourseInfoBySortId(cSortId);

        if (c != null) {
            return courseService.deleteCourseSort(cSortId);
        }
        else {
            throw new AssertionError("该课程类别下的课程不为空，无法删除！");
        }
    }

    //    删除课程
    @RequestMapping(value = "/deleteCourse/{cName}", method = RequestMethod.DELETE)
    public int deleteCourse(@PathVariable(value = "cName") String cName) throws AssertionError {

        int cId = courseService.selectCourseIdInfoByName(cName);
        List<Relations> r = courseService.selectRelationByCid(cId);

        if (r != null) {
            return courseService.deleteCourse(cId);
        }
        else {
            throw new AssertionError("选择该课程的学生不为空，无法删除！");
        }
    }

    //  所有学生信息页面——增加学生
    @RequestMapping(value = "/addNewStudent", method = RequestMethod.PUT)
    public @ResponseBody
    int addOneRelationForCourse(@RequestBody StudentInfo studentInfo) throws Exception {
        StudentInfo s = new StudentInfo();
        s.setuName(studentInfo.getuName());
        s.setuConnect(studentInfo.getuConnect());
        s.setuFingerId(studentInfo.getuFingerId());
        s.setComment(studentInfo.getComment());
        int ret =  studentService.insertStudentInfo(s);
        if (-1 == ret) {
            throw new Exception("学生已存在");
        }
        return 0;
    }

    //  上传签到数据表，返回文件路径URL
    @RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
    public String uploadAttLog(@RequestParam("IMPORTattLog")MultipartFile file) {

        String fileName = file.getOriginalFilename();
        String OriginalFileName = file.getOriginalFilename();
        String postfix=fileName.substring(fileName.lastIndexOf('.'));
        fileName += System.currentTimeMillis();
        fileName = "" + fileName.hashCode() + postfix;

        try {
            URL res = Thread.currentThread().getContextClassLoader().getResource("folders/");
            String fPath = res.getPath() + fileName;
            File fs =  new File(fPath);

//            File fs =  new File("./folders/"+fileName);
            FileOutputStream foss = new FileOutputStream(fs);
            foss.write(file.getBytes());
            foss.close();
            return "{\"name\":\"/folders/"+fileName+"\", \"originalFileName\":\""+OriginalFileName+"\"}";

        }catch (IOException e) {
            return "-1";
        }
    }

    //操作上传文件
    @RequestMapping(value = "/decreaseCourseByFile/{cId}", method = RequestMethod.POST)
    public @ResponseBody
    String changeRelation(@PathVariable("cId") int cId,
                        @RequestBody String attLogFile) throws IOException {
        String root = System.getProperty("user.dir");
        String path = root + File.separator + "target" + File.separator + "classes" + attLogFile;
        BufferedReader reader = new BufferedReader(new FileReader(path));
        String line = null;
        int i = 0;
        while((line=reader.readLine())!=null){
            String item[] = line.split("\t");
            int uId = Integer.parseInt(item[0].trim());
            String attStr = item[1];
            Timestamp arriveTime = Timestamp.valueOf(attStr);
            // 但是此处只检查，第一行的表数据是否已经存在于数据库中，如果是，则代表整个表并未上传过。
            // 如果想要查询该表中的每个数据，可以设置一个bool变量，hasSaved。
            // 或者可以使用MySQL触发器或者MySQL中的"where not exists"
            if (i == 0){
                Attendance attendanceRecord = studentService.selectAttendanceByUid_AttTime(uId,arriveTime);
                if (attendanceRecord!=null)
                    break;
            }
            i++;
            Relations a = courseService.selectRelationBycId_uId(uId, cId);
            int id = a.getId();
            int cNumberLast = a.getcNumberLast();
            cNumberLast = cNumberLast - 1;
            courseService.updateCourseNumber(id, cNumberLast);
            // 将签到数据写入数据库
            Timestamp leaveTime = null;
            String attComment = null;
            studentService.insertAttendance(uId, cId, arriveTime, leaveTime, attComment);

        }
        return "{\"name\":\""+attLogFile+"\"}";
    }

    //个人信息页面——得到该学生本门课的所有签到记录
//    @GetMapping("/allStudentsByCourse/{cId}")
//    public attendTableResult getAttendanceOfStudent(
//            @PathVariable("cId") Integer cId,
//            @RequestParam("offset") int offset,
//            @RequestParam("limit") int limit,
//            @RequestParam("order") String order,
//            @RequestParam("search") String search) {
//
//        attendTableResult result = new attendTableResult();
//        List<Attendance> attendances;
//        int total;
//
//        if (limit > 50) {
//            limit = 50;
//        }
//        if (search == null || search.trim().length() == 0) {
//
//            total = studentService.selectPageStudentCountByCourse(cId);
////          studentInfos = studentService.selectAllStudent();
//
//            if (! (sort == null || sort.trim().length() == 0)) {
//                result.setRows1(studentService.orderStudentByCourseNumber(cId, sort, order, offset, limit));
//
//            }
//            else {
//                studentInfos = studentService.selectStudentPageByCourseId(cId, offset, limit);
////            studentInfos = studentService.orderStudentByCourseNumber(sort,order,offset,limit);
//                result.setRows(studentInfos);
//            }
//
//            result.setTotal(total);
//
//        } else {
//
//            search = "%" + search + "%";
//            studentInfos = studentService.searchStudentByCourse(cId, search, offset, limit);
//            total = studentService.searchStudentCountByCourse(cId, search);
//            result.setTotal(total);
//            result.setRows(studentInfos);
//
//        }
//
//        for (relationTableSearchResultItem it : result.getRows()) {
//
//            Relations r = courseService.selectRelationBycId_uId(it.getuId(), cId);
//            it.setcNumberEd(r.getcNumberEd());
//            it.setcNumberLast(r.getcNumberLast());
//            if (null != r.getOverDate()) {
//                it.setOverDate(r.getOverDate());
//            }
//        }
//
//
//        return result;
//    }


}