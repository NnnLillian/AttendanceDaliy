package com.example.demo.entity;

public class StudentInfo {
    private int uId;
    private int uFingerId;
    private String uName;
    private String uConnect;
    private String comment;

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
}
