package com.example.demo.entity;

import java.util.ArrayList;
import java.util.List;

public class QueryStudentResult {
    public QueryStudentResult() {
        this.items = new ArrayList<StudentInfo>();
        this.retcode = -1;
    }

    public int getRetcode() {
        return retcode;
    }

    public void setRetcode(int retcode) {
        this.retcode = retcode;
    }

    public List<StudentInfo> getItems() {
        return items;
    }

    public void setItems(List<StudentInfo> items) {
        this.items = items;
    }

    private int retcode;
    private List<StudentInfo> items;

}
