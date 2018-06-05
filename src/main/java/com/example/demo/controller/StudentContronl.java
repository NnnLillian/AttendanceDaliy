package com.example.demo.controller;

import com.example.demo.entity.StudentInfo;
import com.example.demo.service.StudentService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentContronl {
    @Autowired
    private StudentService studentService;

    @GetMapping("/getAllStudentInfo")
    public String index(Model model){
        List<StudentInfo> studentInfos = studentService.selectAllStudent();
        model.addAttribute( "studentInfos", studentInfos);
        return "allStuInfo";
    }

    @PostMapping("/insertStudentInfo")
    public String insert(@ModelAttribute StudentInfo studentInfo){
        studentService.insertStudentInfo(studentInfo);
        return "addNewStu" ;
    }

    @GetMapping("/getOneStudentInfo")
    public String getOneStudent(@RequestParam("uId") int uId, Model model){
        StudentInfo studentInfo = studentService.selectStudentById(uId);
        model.addAttribute("studentInfo",studentInfo);
        model.addAttribute("uId",uId);
        return "studentInfo_per";
    }
}

