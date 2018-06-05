package com.example.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;

public class Relations {

    private int id;
    private int uId;
    private int cId;
    private int cNumberEd;
    private int cNumberLast;


    private String cName;
    private Date overDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }
    public Date getOverDate() {
        return overDate;
    }

    public void setOverDate(Date overDate) {
        this.overDate = overDate;
    }
}
