package com.example.demo.entity;



public class CourseInfo {
    private int cId;
    private int cSortId;
    private String cName;
    private String cImgUrl;


    private String cSortName;

    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }

    public int getcSortId() {
        return cSortId;
    }

    public void setcSortId(int cSortId) {
        this.cSortId = cSortId;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getcImgUrl(){ return cImgUrl;}

    public void setcImgUrl(){this.cImgUrl = cImgUrl;}


    public String getcSortName() {
        return cSortName;
    }

    public void setcSortName(String cSortName) {
        this.cSortName = cSortName;
    }

}
