package com.okosotthon.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TartozikRepository {
    JdbcTemplate jdbc;

    public TartozikRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public int save(int actualUserId, String lakasnev) {
        String select = "(SELECT lakas.id from lakas WHERE lakasnev LIKE '"+lakasnev+"')";
        String sql = "INSERT INTO tartozik (felh_id,lakas_id) VALUES ("+actualUserId+","+select+")";
        return jdbc.update(sql);
    }

    public int deleteUserFromLakas(int id) {
        String sql = "DELETE FROM tartozik WHERE felh_id="+id;
        return jdbc.update(sql);
    }
}
