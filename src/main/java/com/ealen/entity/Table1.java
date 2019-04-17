package com.ealen.entity;

import java.sql.Date;

public class Table1 {

    private int c1;
    private String c2;
    private String c3;
    private Date c4;

    public Table1(int c1, String c2, String c3,Date c4) {
        this.c1 = c1;
        this.c2 = c2;
        this.c3 = c3;
        this.c4 = c4;
    }

    public int getC1() {
        return c1;
    }

    public void setC1(int c1) {
        this.c1 = c1;
    }

    public String getC2() {
        return c2;
    }

    public void setC2(String c2) {
        this.c2 = c2;
    }

    public String getC3() {
        return c3;
    }

    public void setC3(String c3) {
        this.c3 = c3;
    }

    public Date getC4() {
        return c4;
    }

    public void setC4(Date c4) {
        this.c4 = c4;
    }
}
