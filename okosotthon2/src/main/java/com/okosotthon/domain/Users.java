package com.okosotthon.domain;

import lombok.Data;

//@Entity

public class Users {
    /*@Id
    @GeneratedValue(strategy = GenerationType.AUTO)*/
    private int id;
    private String felh;
    private String jelszo;
    private String email;
    private String role;
    /*@OneToMany(mappedBy="lakasId")
    @JoinTable(name="lakas",
            joinColumns = {@JoinColumn(name = "id", referencedColumnName = "lakasId")},
            inverseJoinColumns = {@JoinColumn(name = "id", referencedColumnName = "lakasID")})*/
    private Szoba szoba;
    public Users(){}

    public Users(int id, String felh, String jelszo, String email,String role) {
        this.id = id;
        this.felh = felh;
        this.jelszo = jelszo;
        this.email = email;
        this.role = role;
    }

    /*public Users(String felh, String jelszo, String email, int lakasid) {
        this.felh = felh;
        this.jelszo = jelszo;
        this.email = email;
        this.lakasid = lakasid;
    }*/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFelh() {
        return felh;
    }

    public void setFelh(String felh) {
        this.felh = felh;
    }

    public String getJelszo() {
        return jelszo;
    }

    public void setJelszo(String jelszo) {
        this.jelszo = jelszo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
