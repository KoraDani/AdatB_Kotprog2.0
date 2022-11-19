package com.okosotthon.repository;

import com.okosotthon.domain.Adatok;
import com.okosotthon.domain.Szoba;
import com.okosotthon.domain.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class SzobaRepository {

    private JdbcTemplate jdbc;

    @Autowired
    public SzobaRepository(JdbcTemplate jdbc){
        this.jdbc = jdbc;
    }

    public List<Szoba> getAllSzoba(){
        String sql = "Select * from szoba";
        return jdbc.query(sql,(rs,i)-> new Szoba(
                rs.getInt("id"),
                rs.getInt("lakas_id"),
                rs.getString("szobaNev"),
                rs.getInt("terulet")
        ));
    }

//    public List<Szoba> getAllUserSzoba(int felhid){
//        String sql = "Select * from szoba INNER JOIN lakas ON lakas.id=szoba.lakas_id INNER JOIN tartozik ON tartozik.lakas_id=lakas.id where tartozik.felh_id="+felhid;
//        return jdbc.query(sql,(rs,i)-> new Szoba(
//                rs.getInt("id"),
//                rs.getInt("lakas_id"),
//                rs.getString("szobaNev"),
//                rs.getInt("terulet")
//        ));
//    }

    public List<Szoba> getAllUserSzoba(int felhid){
        String sql = "Select szoba.szobaNev, eszkozok.eszkozNev, futes.futesTipus, szoba.terulet from szoba INNER JOIN lakas ON lakas.id=szoba.lakas_id INNER JOIN tartozik ON tartozik.lakas_id=lakas.id INNER JOIN eszkozok ON szoba.id=eszkozok.szobaID INNER JOIN futes ON szoba.id=futes.szobaID where tartozik.felh_id="+felhid;
        return jdbc.query(sql,(rs,i)-> new Szoba(
                rs.getString("szobaNev"),
                rs.getString("eszkozNev"),
                rs.getString("futesTipus"),
                rs.getInt("terulet")
        ));
    }

    public List<Szoba> getAllUserSzobaAdat(int lakasid){
        String sql = "Select szoba.szobaNev, adatok.homerseklet, adatok.paratartalom from szoba INNER JOIN lakas ON lakas.id=szoba.lakas_id INNER JOIN tartozik ON tartozik.lakas_id=lakas.id INNER JOIN adatok ON szoba.id=adatok.szobaId WHERE tartozik.felh_id="+lakasid+" GROUP BY szoba.szobaNev ORDER BY adatok.datum DESC";
        return jdbc.query(sql,(rs,i)-> new Szoba(
                rs.getString("szobaNev"),
                rs.getInt("homerseklet"),
                rs.getInt("paratartalom")
        ));
    }


    /*public List<Szoba> getAllUserSzobaAdat(int felh){
        String sql = "Select szoba.szobaNev, adatok.homerseklet,adatok.paratartalom, eszkozok.eszkozNev from szoba "+
    "INNER JOIN lakas ON lakas.szoba_id=szoba.id "+
    "INNER JOIN users ON lakas.felh_id=users.id "+
    "INNER JOIN adatok On szoba.id=adatok.szobaId "+
    "INNER JOIN tobbeszkoz on tobbeszkoz.szobaID=szoba.id "+
    "INNER JOIN eszkozok on tobbeszkoz.eszkozID=eszkozok.id "+
    "where users.id="+felh;
        return jdbc.query(sql,(rs,i)-> new Szoba(
                rs.getString("szobaNev"),
                rs.getInt("homerseklet"),
                rs.getInt("paratartalom"),
                rs.getString("eszkozNev")
        ));
    }*/
    public int save(int uid,String szobaNev, int terulet){
        String select = "(SELECT lakas_id FROM tartozik WHERE felh_id="+uid+" LIMIT 1)";
        String sql = "INSERT INTO szoba (lakas_id,szobaNev, terulet) VALUES ("+select+",'"+szobaNev+"',"+terulet+")";
        return jdbc.update(sql);
    }

    public int update(Szoba szoba){
        String sql = "UPDATE szoba SET szobaNev="+szoba.getSzobanev()+",terulet="+szoba.getTerulet();
        return jdbc.update(sql);
    }

    public int delete(int id){
        String sql = "DELETE FROM szoba WHERE szoba.id="+id;
        return jdbc.update(sql);
    }
}
