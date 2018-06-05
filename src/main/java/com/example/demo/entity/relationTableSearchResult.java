package com.example.demo.entity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class relationTableSearchResult {

    private int total;
    private List<relationTableSearchResultItem> rows;

    public relationTableSearchResult(){
        this.total =0;
        this.rows = new ArrayList<relationTableSearchResultItem>();
    }

    public relationTableSearchResult(int total, List<StudentInfo> studentInfos) {
        this.total = total;
        this.rows = new ArrayList<relationTableSearchResultItem>();
        for(StudentInfo s: studentInfos) {
            this.rows.add(new relationTableSearchResultItem(s));
        }
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<relationTableSearchResultItem> getRows() {
        return rows;
    }

    public void setRows(List<StudentInfo> rows) {
        this.rows.clear();
        for(StudentInfo s: rows) {
            this.rows.add(new relationTableSearchResultItem(s));
        }
    }

    public void setRows1(List<relationTableSearchResultItem> rows) {
        this.rows = rows;
    }
}
