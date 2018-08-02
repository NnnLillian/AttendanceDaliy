package com.example.demo.entity;

import java.util.ArrayList;
import java.util.List;

public class attendTableResult {

    private int total;
    private List<attendTableResultItem> rows;

    public attendTableResult(){
        this.total = 0;
        this.rows = new ArrayList<attendTableResultItem>();
    }

    public attendTableResult(int total, List<Attendance> attendances){
        this.total = total;
        this.rows = new ArrayList<attendTableResultItem>();
        for(Attendance a: attendances){
            this.rows.add(new attendTableResultItem(a));
        }
    }

    public int getTotal(){
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<attendTableResultItem> getRows() {
        return rows;
    }

    public void setRows(List<Attendance> rows) {
        this.rows.clear();
        for(Attendance a: rows) {
            this.rows.add(new attendTableResultItem(a));
        }
    }

    public void setRows1(List<attendTableResultItem> rows) {
        this.rows = rows;
    }
}
