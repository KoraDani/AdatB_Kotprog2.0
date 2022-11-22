package com.okosotthon.domain;

import java.util.Date;

//@Entity
public class Adatok {
    /*@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "adatokid")*/
    private long id;
    private int szobaid;
    private int homerseklet;
    private int paratartalom;
    private Date datum;

    /*@ManyToOne
    @MapsId
    @JoinColumn(name = "adatokid")*/
    private Szoba szoba;

    public Adatok(){}

    public Adatok(long id, int szobaid, int homerseklet, int paratartalom, Date datum) {
        this.id = id;
        this.szobaid = szobaid;
        this.homerseklet = homerseklet;
        this.paratartalom = paratartalom;
        this.datum = datum;
    }
    public Adatok(long id, int szobaid, int homerseklet, int paratartalom) {
        this.id = id;
        this.szobaid = szobaid;
        this.homerseklet = homerseklet;
        this.paratartalom = paratartalom;
    }
    public Adatok( int homerseklet, int paratartalom) {
        this.homerseklet = homerseklet;
        this.paratartalom = paratartalom;
    }

    public Adatok(long id, int szobaid, int homerseklet, int paratartalom, Date datum, String szobanev){
        this.id = id;
        this.szobaid = szobaid;
        this.homerseklet = homerseklet;
        this.paratartalom = paratartalom;
        this.datum = datum;
        this.szoba = new Szoba(szobanev);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getSzobaid() {
        return szobaid;
    }

    public void setSzobaid(int szobaid) {
        this.szobaid = szobaid;
    }

    public int getHomerseklet() {
        return homerseklet;
    }

    public void setHomerseklet(int homerseklet) {
        this.homerseklet = homerseklet;
    }

    public int getParatartalom() {
        return paratartalom;
    }

    public void setParatartalom(int paratartalom) {
        this.paratartalom = paratartalom;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public Szoba getSzoba() {
        return szoba;
    }

    public void setSzoba(Szoba szoba) {
        this.szoba = szoba;
    }
}
