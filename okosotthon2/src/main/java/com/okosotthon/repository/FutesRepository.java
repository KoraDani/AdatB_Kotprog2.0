package com.okosotthon.repository;

import com.okosotthon.domain.Adatok;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class FutesRepository {
    JdbcTemplate jdbc;

    @Autowired
    public void setJdbc(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public int save(String szobanev,String futesT){
        String select = "(SELECT id FROM szoba where szobaNev LIKE '"+szobanev+"')";
        String sql = "INSERT INTO futes (szobaID, futesTipus, fokozat, bekapcsolva) VALUES ("+select+",'"+futesT+"',1,0)";
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
}
