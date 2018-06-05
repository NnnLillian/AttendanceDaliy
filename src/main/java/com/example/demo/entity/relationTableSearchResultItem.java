package com.example.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;


public class relationTableSearchResultItem{


    private int uId;
    private int uFingerId;
    private String uName;
    private String uConnect;
    private String comment;
    private int cNumberEd;
    private int cNumberLast;
    private Date overDate;

    public relationTableSearchResultItem(){}

    public relationTableSearchResultItem(StudentInfo studentInfo) {
        this.uId = studentInfo.getuId();
        this.uFingerId = studentInfo.getuFingerId();
        this.uName = studentInfo.getuName();
        this.uConnect = studentInfo.getuConnect();
        this.comment = studentInfo.getComment();
        this.cNumberEd = -1;
        this.cNumberLast = -1;
        this.overDate = new Date(System.currentTimeMillis());
    }

    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    public int getuFingerId() {
        return uFingerId;
    }

    public void setuFingerId(int uFingerId) {
        this.uFingerId = uFingerId;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getuConnect() {
        return uConnect;
    }

    public void setuConnect(String uConnect) {
        this.uConnect = uConnect;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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
