package com.example.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;

public class StudentRelationTableSearchResultItem {

    private int cId;
    private int cSortId;
    private String cName;
    private String cImgUrl;
    private int cNumberEd;
    private int cNumberLast;
    private Date overDate;

    public StudentRelationTableSearchResultItem(CourseInfo courseInfo) {
        this.cId = courseInfo.getcId();
        this.cName = courseInfo.getcName();
        this.cSortId = courseInfo.getcSortId();
        this.cImgUrl = courseInfo.getcImgUrl();
        this.cNumberEd = -1;
        this.cNumberLast = -1;
        this.overDate = new Date(System.currentTimeMillis());
    }

    public int getcId() {
        return cId;
    }

    public void setcId(int uId) {
        this.cId = cId;
    }

    public String getcName() {
        return cName;
    }

    public void setcNamee(String cName) {
        this.cName = cName;
    }

    public int getcSortId() {
        return cSortId;
    }

    public void setcSortId(String uConnect) {
        this.cSortId = cSortId;
    }

    public String getcImgUrl() {
        return cImgUrl;
    }

    public void setcImgUrl(String cImgUrl) {
        this.cImgUrl = cImgUrl;
    }

    public int getcNumberEd() {
        return cNumberEd;
    }

    public void setcNumberEd(int cNumberEd) {
        this.cNumberEd = cNumberEd;
    }

    public int getcNumberLast() {
        return cNumberLast;
    }

    public void setcNumberLast(int cNumberLast) {
        this.cNumberLast = cNumberLast;
    }

    public Date getOverDate() {
        return overDate;
    }

    public void setOverDate(Date overDate) {
        this.overDate = overDate;
    }
}
