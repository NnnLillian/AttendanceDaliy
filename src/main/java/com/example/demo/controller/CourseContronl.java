package com.example.demo.controller;

import com.example.demo.entity.CourseInfo;
import com.example.demo.entity.CourseSort;
import com.example.demo.entity.Relations;
import com.example.demo.entity.StudentInfo;
import com.example.demo.mapper.CourseInfoMapper;
import com.example.demo.mapper.CourseSortMapper;
import com.example.demo.service.CourseService;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.List;

@Controller
@RequestMapping("/course")
public class CourseContronl {
    @Autowired
    private CourseService courseService;
    @Autowired
    private StudentService studentService;

    @GetMapping("/getAllcourseSort")
    public String getAllcourseSort(Model model){
//        List指的是集合.<>是泛型,里面指定了这个集合中存放的是什么数据.
        List<CourseSort> courseSorts = courseService.selectAll();
        model.addAttribute("sortInfos",courseSorts);
        return "courseSortInfo";
    }

    @GetMapping("/getCourseBySort")
    public String getCourseBySort(@RequestParam("cSortId") int cSortId, Model model){

        List<CourseInfo> courseInfo;
        CourseSort cSort;

        cSort = courseService.selectCourseSortById(cSortId);

        if (null == cSort) {
            // ...
            System.out.println("Error");
            return "error";
        }

        else {

            courseInfo = courseService.selectCourseInfoBySortId(cSortId);

            model.addAttribute("courseInfos", courseInfo);
            model.addAttribute("cSortId", cSortId);
            model.addAttribute("cSortName", cSort.getcSortName());
            List<CourseSort> courseSorts = courseService.selectAll();
            model.addAttribute("sortInfos", courseSorts);

            return "courseInfo_art";

        }
    }

    @GetMapping("/getCourseBySortForStudent")
    public String getCourseBySortForStudent(@RequestParam("cSortId") int cSortId,
                                            @RequestParam("cId")int cId, Model model){

        CourseSort cSort;
        List<CourseSort> allCourseSort;
        cSort = courseService.selectCourseSortById(cSortId);
        allCourseSort = courseService.selectAllCourseSort();


        if (null == cSort || null ==allCourseSort) {
            // ...
            System.out.println("Error");
            return "error";
        }
        else {
            CourseInfo courseInfo = courseService.selectCourseInfoById(cId);
            List<StudentInfo> studentInfos = studentService.selectStudentInfoByCourseId(cId);
            model.addAttribute("cSort",cSort);
            model.addAttribute("cId",cId);
            model.addAttribute("allCourseSort",allCourseSort);
            model.addAttribute("courseInfo", courseInfo);
            model.addAttribute("studentList", studentInfos);

            return "courseInfo_art_stu";

        }
    }


    @GetMapping("/uploadFile")
    public String decreaseCourseNumber(@RequestParam("cSortId") int cSortId, Model model){

        List<CourseInfo> courseInfo;
        CourseSort cSort;

        cSort = courseService.selectCourseSortById(cSortId);

        if (null == cSort) {
            // ...
            System.out.println("Error");
            return "error";
        }else {

            courseInfo = courseService.selectCourseInfoBySortId(cSortId);

            model.addAttribute("courseInfos", courseInfo);
            model.addAttribute("cSortId", cSortId);
            model.addAttribute("cSortName", cSort.getcSortName());
            List<CourseSort> courseSorts = courseService.selectAll();
            model.addAttribute("sortInfos", courseSorts);

            return "uploadTable";

        }
    }

}
