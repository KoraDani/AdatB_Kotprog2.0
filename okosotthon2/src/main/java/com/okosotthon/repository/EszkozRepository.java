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

    public List<Eszkozok> getAllUserEszkoz(long uid){
        String sql = "select eszkozok.eszkozNev, szoba.szobaNev from eszkozok INNER JOIN szoba on szoba.id=eszkozok.szobaID INNER JOIN lakas ON lakas.id=szoba.lakas_id INNER JOIN tartozik ON tartozik.lakas_id=lakas.id WHERE tartozik.felh_id="+uid;
        return jdbc.query(sql, (rs, i) -> new Eszkozok(
                    rs.getString("eszkozNev"),
                    rs.getString("szobaNev")
                ));
    }

    public int addNewEszkoz(String szobanev, String eszkozNev) {
        String sql = "INSERT INTO eszkozok(szobaID,eszkozNev, bekapcsolva) VALUES((SELECT id FROM szoba WHERE szobaNev LIKE '"+szobanev+"' LIMIT 1),'"+eszkozNev+"',0);";
        return jdbc.update(sql);
    }

    public int update(long szobaid, String eszkoznev){
        String sql = "UPDATE eszkozok SET eszkozNev='"+eszkoznev+"' WHERE szobaID="+szobaid;
        return jdbc.update(sql);
    }

    public int delete(int id){
        String sql = "DELETE FROM eszkozok WHERE eszkozok.szobaID="+id;
        return jdbc.update(sql);
    }
}
