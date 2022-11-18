package com.okosotthon.domain;



public class Futes {
    /*@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="futesid")*/
    private int id;
    private String futestipus;
    private int fokozat;
    private int bekapcsolva;

    public Futes(){}

    public Futes(int id, String futestipus) {
        this.id = id;
        this.futestipus = futestipus;
    }
    public Futes(String futestipus){
        this.futestipus = futestipus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFutestipus() {
        return futestipus;
    }

    public void setFutestipus(String futestipus) {
        this.futestipus = futestipus;
    }

    public int getFokozat() {
        return fokozat;
    }

    public void setFokozat(int fokozat) {
        this.fokozat = fokozat;
    }

    public int getBekapcsolva() {
        return bekapcsolva;
    }

    public void setBekapcsolva(int bekapcsolva) {
        this.bekapcsolva = bekapcsolva;
    }
}
