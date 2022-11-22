package com.okosotthon.repository;

import com.okosotthon.domain.Adatok;
import com.okosotthon.domain.Szoba;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Repository
public class AdatokRepository {
    private JdbcTemplate jdbc;

    @Autowired
    public AdatokRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    //
    public List<Adatok> getSzobaAdatok(long szobaID){
        String sql = "SELECT adatok.id, adatok.szobaId, adatok.homerseklet, adatok.paratartalom, adatok.datum, szoba.szobaNev FROM adatok INNER JOIN szoba ON adatok.szobaId=szoba.id WHERE adatok.szobaId="+szobaID+" ORDER by adatok.datum DESC LIMIT 1";
        return  jdbc.query(sql,(rs, i)-> new Adatok(
                rs.getInt("id"),
                rs.getInt("szobaId"),
                rs.getInt("homerseklet"),
                rs.getInt("paratartalom"),
                rs.getDate("datum")
        ));

    }

    //TODO Adatok CRUD műveletek megcsinálása
    public int save(Adatok adatok){
        String sql = "INSERT INTO adatok (szobaId, homerseklet, paratartalom, datum) VALUES ("+adatok.getSzobaid()+","+adatok.getHomerseklet()+","+adatok.getParatartalom()+","+adatok.getDatum()+")";
        return jdbc.update(sql);
    }

    public int update(Adatok adatok){
        String sql = "UPDATE adatok SET szobaId="+adatok.getSzobaid()+",homerseklet="+adatok.getHomerseklet()+",paratartalom="+adatok.getParatartalom()+",datum="+adatok.getDatum()+" WHERE adatok.szobaId="+adatok.getSzobaid();
        return jdbc.update(sql);
    }

    public int delete(int id){
        String sql = "DELETE FROM adatok WHERE adatok.id="+id;
        return jdbc.update(sql);
    }

    public List<Adatok> getAllUserAdatok(int id) {
        String sql = "select adatok.id, adatok.szobaId, adatok.homerseklet, adatok.paratartalom, adatok.datum, szoba.szobaNev from adatok INNER JOIN szoba ON adatok.szobaId=szoba.id INNER JOIN lakas ON lakas.id=szoba.lakas_id INNER JOIN tartozik ON tartozik.lakas_id=lakas.id WHERE tartozik.felh_id="+id;
        return jdbc.query(sql,(rs, i)-> new Adatok(
                rs.getInt("id"),
                rs.getInt("szobaId"),
                rs.getInt("homerseklet"),
                rs.getInt("paratartalom"),
                rs.getDate("datum"),
                rs.getString("szobaNev")
        ));
    }
}
