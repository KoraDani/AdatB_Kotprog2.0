package com.okosotthon.domain;



public class Lakas {
    /*@Id
    @GeneratedValue(strategy = GenerationType.AUTO)*/
    private int id;
    private String lakasnev;

    public Lakas(){}

    public Lakas(int id, String lakasnev) {
        this.id = id;
        this.lakasnev = lakasnev;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLakasnev() {
        return lakasnev;
    }

    public void setLakasnev(String lakasnev) {
        this.lakasnev = lakasnev;
    }
}
