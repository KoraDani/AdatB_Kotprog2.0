package com.okosotthon.repository;

import com.okosotthon.domain.Adatok;
import com.okosotthon.domain.Eszkozok;
import com.okosotthon.domain.Futes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FutesRepository {
    JdbcTemplate jdbc;

    @Autowired
    public FutesRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    //TODO select megcsinálása
    public int save(String szobanev,String futesT){
        String select = "(SELECT id FROM szoba where szobaNev LIKE '"+szobanev+"')";
        String sql = "INSERT INTO futes (szobaID, futesTipus, fokozat, bekapcsolva) VALUES ("+select+",'"+futesT+"',1,0)";
        return jdbc.update(sql);
    }

    public int update(long szobaid, String futestipus){
        String sql = "UPDATE futes SET futesTipus='"+futestipus+"' WHERE szobaID="+szobaid;
        return jdbc.update(sql);
    }

    public int delete(int id){
        String sql = "DELETE FROM adatok WHERE adatok.szobaID="+id;
        return jdbc.update(sql);
    }

    public List<Futes> getAllUserFutes(int uid) {
        String sql = "select futes.futesTipus, szoba.szobaNev from futes INNER JOIN szoba on szoba.id=futes.szobaID INNER JOIN lakas ON lakas.id=szoba.lakas_id INNER JOIN tartozik ON tartozik.lakas_id=lakas.id WHERE tartozik.felh_id="+uid;
        return jdbc.query(sql, (rs, i) -> new Futes(
                rs.getString("futesTipus"),
                rs.getString("szobaNev")
        ));
    }
}
