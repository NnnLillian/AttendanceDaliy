package com.example.demo.entity;

import java.sql.Timestamp;

public class attendTableResultItem {

    private int attId;
    private int uId;
    private int cId;
    private Timestamp arriveTime;
    private Timestamp leaveTime;
    private String attComment;

    public attendTableResultItem(){}

    public attendTableResultItem(Attendance attendance){
        this.attId = attendance.getAttId();
        this.uId = attendance.getuId();
        this.cId = attendance.getcId();
        this.arriveTime = attendance.getArriveTime();
        this.leaveTime = attendance.getLeaveTime();
        this.attComment = attendance.getAttComment();
    }

    public int getAttId() {
        return attId;
    }

    public void setAttId(int attId) {
        this.attId = attId;
    }

    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }

    public Timestamp getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(Timestamp arriveTime) {
        this.arriveTime = arriveTime;
    }

    public Timestamp getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(Timestamp leaveTime) {
        this.leaveTime = leaveTime;
    }

    public String getAttComment() {
        return attComment;
    }

    public void setAttComment(String attComment) {
        this.attComment = attComment;
    }
}
