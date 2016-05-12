package com.example.sarvesh.mytime;

import java.io.Serializable;

/**
 * Created by Sarvesh on 4/13/2016.
 */
public class Subject implements Serializable
{

    public Subject()
    {
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAtt() {
        return att;
    }

    public void setAtt(double att) {
        this.att = att;
    }

    public double getAtts() {
        return atts;
    }

    public void setAtts(double atts) {
        this.atts = atts;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    private double atts;
    private double total;
    private String name;
    private  double att;
}
