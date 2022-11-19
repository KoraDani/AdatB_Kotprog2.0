package com.okosotthon.repository;

import com.okosotthon.domain.Adatok;
import com.okosotthon.domain.Eszkozok;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EszkozRepository {

    private JdbcTemplate jdbc;

    @Autowired
    public EszkozRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public Eszkozok getAllUserEszkoz(long szobaId){
        String sql = "select szoba.szobaNev, eszkozok.eszkozNev from eszkozok INNER JOIN szoba on szoba.id=eszkozok.szobaID where eszkozok.szobaID="+szobaId;
        return jdbc.queryForObject(sql, new Eszkozok[]{}, (rs, rowNum) ->
                new Eszkozok(
                   rs.getString("eszkozNev"),
                        rs.getString("szobaNev")
                ));
    }

    public int addNewEszkoz(String szobanev, String eszkozNev) {
        String sql = "INSERT INTO eszkozok(szobaID,eszkozNev, bekapcsolva) VALUES((SELECT id FROM szoba WHERE szobaNev LIKE '"+szobanev+"' LIMIT 1),'"+eszkozNev+"',0);";
        return jdbc.update(sql);
    }

    public int update(Eszkozok eszkozok){
        String sql = "UPDATE eszkozok SET eszkozNev="+eszkozok.getEszkoznev();
        return jdbc.update(sql);
    }

    public int delete(int id){
        String sql = "DELETE FROM eszkozok WHERE eszkozok.szobaID="+id;
        return jdbc.update(sql);
    }
}
