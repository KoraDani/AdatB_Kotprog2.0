package com.okosotthon.domain;


public class Eszkozok {
    /*@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "eszkozid")*/
    private int id;
    private int szobaID;
    private String eszkoznev;
    private int bekapcsolva;
    private Szoba szoba;

    public Eszkozok(){}

    public Eszkozok(int id, String eszkoznev) {
        this.id = id;
        this.eszkoznev = eszkoznev;
    }
    public Eszkozok(String eszkoznev, String szobanev){
        this.eszkoznev = eszkoznev;
        this.szoba = new Szoba(szobanev);
    }
    public Eszkozok(String eszkoznev){
        this.eszkoznev = eszkoznev;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEszkoznev() {
        return eszkoznev;
    }

    public void setEszkoznev(String eszkoznev) {
        this.eszkoznev = eszkoznev;
    }

    public int getSzobaID() {
        return szobaID;
    }

    public void setSzobaID(int szobaID) {
        this.szobaID = szobaID;
    }

    public int getBekapcsolva() {
        return bekapcsolva;
    }

    public void setBekapcsolva(int bekapcsolva) {
        this.bekapcsolva = bekapcsolva;
    }

    public Szoba getSzoba() {
        return szoba;
    }

    public void setSzoba(Szoba szoba) {
        this.szoba = szoba;
    }
}
