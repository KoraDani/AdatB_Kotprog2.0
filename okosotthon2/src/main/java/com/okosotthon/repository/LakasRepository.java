package com.okosotthon.repository;

import com.okosotthon.domain.Adatok;
import com.okosotthon.domain.Lakas;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LakasRepository {
    private JdbcTemplate jdbc;

    public LakasRepository(JdbcTemplate jdbc){
        this.jdbc = jdbc;
    }

    public List<Lakas> getAllLakas(){
        String sql = "Select * from lakas";
        return jdbc.query(sql,(rs,i)-> new Lakas(
                rs.getInt("id"),
                rs.getString("lakasnev")
        ));
    }

    public List<Lakas> getAllUserLakas(int felhid){
        String sql = "Select lakas.id,lakas.lakasnev from lakas INNER JOIN tartozik on tartozik.lakas_id=lakas.id WHERE felh_id="+felhid;
        return jdbc.query(sql,(rs,i)-> new Lakas(
                rs.getInt("id"),
                rs.getString("lakasnev")
        ));
    }
    public int save(String lakasnev){
        String sql = "INSERT INTO lakas (lakasnev) VALUES ('"+lakasnev+"')";
        return jdbc.update(sql);
    }

    public int update(Lakas lakas){
        String sql = "UPDATE lakas SET id="+lakas.getId()+",lakasnev="+lakas.getLakasnev();
        return jdbc.update(sql);
    }

    public int delete(int id){
        String sql = "DELETE FROM lakas WHERE lakas.felh_id="+id;
        return jdbc.update(sql);
    }

}
