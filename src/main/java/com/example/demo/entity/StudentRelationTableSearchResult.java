package com.example.demo.entity;

import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;

public class StudentRelationTableSearchResult {


    private int total;
    private List<StudentRelationTableSearchResultItem> rows;

    public StudentRelationTableSearchResult(){
        this.total =0;
        this.rows = new ArrayList<StudentRelationTableSearchResultItem>();
    }

    public StudentRelationTableSearchResult(int total, List<CourseInfo> courseInfos) {
        this.total = total;
        this.rows = new ArrayList<StudentRelationTableSearchResultItem>();
        for(CourseInfo c: courseInfos) {
            this.rows.add(new StudentRelationTableSearchResultItem(c));
        }
    }


    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<StudentRelationTableSearchResultItem> getRows() {
        return rows;
    }

    public void setRows(List<CourseInfo> rows) {
        this.rows.clear();
        for (CourseInfo c: rows){
            this.rows.add(new StudentRelationTableSearchResultItem(c));
        }
    }
}
