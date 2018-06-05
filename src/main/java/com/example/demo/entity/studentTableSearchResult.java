package com.example.demo.entity;

import java.util.List;
import java.util.ArrayList;

public class studentTableSearchResult {

    int total;
    List<StudentInfo> rows;


    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<StudentInfo> getRows() {
        return rows;
    }

    public void setRows(List<StudentInfo> rows) {
        this.rows = rows;
    }

    public studentTableSearchResult() {
        total = 0;
        rows = new ArrayList<StudentInfo>();
    }
}
