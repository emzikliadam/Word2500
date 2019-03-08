package com.cerezlikapp.calengar.calengar500v;

import java.io.Serializable;

public class a2notlar implements Serializable{
    private int id;
    private String ingilizce;
    private String turkce;

    public a2notlar() {
    }

    public a2notlar(int id, String ingilizce, String turkce) {
        this.id = id;
        this.ingilizce = ingilizce;
        this.turkce = turkce;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIngilizce() {
        return ingilizce;
    }

    public void setIngilizce(String ingilizce) {
        this.ingilizce = ingilizce;
    }

    public String getTurkce() {
        return turkce;
    }

    public void setTurkce(String turkce) {
        this.turkce = turkce;
    }
}
