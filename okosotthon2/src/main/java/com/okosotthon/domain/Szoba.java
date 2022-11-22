package com.okosotthon.domain;


import java.util.Date;
import java.util.List;

//@Entity
public class Szoba {
    /*@Id
    @GeneratedValue(strategy = GenerationType.AUTO)*/
    private long id;
    private int lakasid;
    private String szobanev;
    private int terulet;
    private Adatok adatok;
    /*@OneToMany(mappedBy="id")
    @JoinTable(name="tobbfutes",
            joinColumns = {@JoinColumn(name = "szobaID", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "futesID", referencedColumnName = "futesid")})*/
    private Futes futes;

    /*@OneToMany(mappedBy = "id")
    @JoinTable(name="tobbeszkoz",
            joinColumns = {@JoinColumn(name = "szobaID", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "eszkozID", referencedColumnName = "eszkozid")})*/
    private Eszkozok eszkozok;

    /*@OneToMany(mappedBy = "id")
    @PrimaryKeyJoinColumn*/



    public Szoba() {}
    public Szoba(String szobanev){
        this.szobanev = szobanev;
    }

    public Szoba(long id, int lakasid, String szobanev, int terulet) {
        this.id = id;
        this.lakasid = lakasid;
        this.szobanev = szobanev;
        this.terulet = terulet;
    }
    public Szoba(String szobanev, int homerseklet, int paratartlom) {
        this.szobanev = szobanev;
        this.adatok = new Adatok(homerseklet,paratartlom);
    }

    public Szoba(long id,String szobanev, String eszkoznev, String futesT, int terulet){
        this.id = id;
        this.szobanev = szobanev;
        this.eszkozok = new Eszkozok(eszkoznev);
        this.futes = new Futes(futesT);
        this.terulet = terulet;
    }

    public Adatok getAdatok() {
        return adatok;
    }

    public void setAdatok(Adatok adatok) {
        this.adatok = adatok;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getLakasid() {
        return lakasid;
    }

    public void setLakasid(int lakasid) {
        this.lakasid = lakasid;
    }

    public String getSzobanev() {
        return szobanev;
    }

    public void setSzobanev(String szobanev) {
        this.szobanev = szobanev;
    }

    public int getTerulet() {
        return terulet;
    }

    public void setTerulet(int terulet) {
        this.terulet = terulet;
    }

    public Eszkozok getEszkozok() {
        return eszkozok;
    }

    public void setEszkozok(Eszkozok eszkozok) {
        this.eszkozok = eszkozok;
    }

    public Futes getFutes() {
        return futes;
    }

    public void setFutes(Futes futes) {
        this.futes = futes;
    }
}
