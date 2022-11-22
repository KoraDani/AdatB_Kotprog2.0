package com.okosotthon.repository;

import com.okosotthon.domain.Adatok;
import com.okosotthon.domain.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UsersRepository{

    private JdbcTemplate jdbc;

    @Autowired
    public UsersRepository(JdbcTemplate jdbc){
        this.jdbc = jdbc;
    }

    public List<Users> getAllUsers(){
        String sql = "Select * from users";
        return jdbc.query(sql,(rs,i)-> new Users(
                rs.getInt("id"),
                rs.getString("felh"),
                rs.getString("jelszo"),
                rs.getString("emial"),
                rs.getString("role")
        ));
    }

    public void updateUserData(String newname, String newemail, int felhid) {
        String sql = "Update users SET ";
        if(newname != null && !newname.equals("")){
            sql += "felh='"+newname+"' ";
        }if(newemail != null && !newemail.equals("")){
            sql += "emial='"+newemail+"' ";
        }
        sql += "WHERE users.id="+felhid;
        jdbc.update(sql);
    }

    public void updateUserPassword(String newpwd1, int felhid){
        String sql = "Update users SET jelszo='"+newpwd1+"' WHERE users.id="+felhid;
        jdbc.update(sql);
    }

    public void save(Users users) {
        String sql = "INSERT INTO users(felh, jelszo, emial, role) VALUES ('"+users.getFelh()+"','"+users.getJelszo()+"','"+users.getEmail()+"','USER')";
        jdbc.update(sql);
    }

    public int delete(int id){
        String sql = "DELETE FROM users WHERE users.id="+id;
        return jdbc.update(sql);
    }

    public Users findByUsername(String username) {
        String sql = "SELECT id,felh,jelszo,emial,role FROM users WHERE felh LIKE '"+username+"'";
        List<Users> uList = jdbc.query(sql,(rs,i)-> new Users(
                rs.getInt("id"),
                rs.getString("felh"),
                rs.getString("jelszo"),
                rs.getString("emial"),
                rs.getString("role")
        ));

        return new Users(uList.get(0).getId(),uList.get(0).getFelh(),uList.get(0).getJelszo(),uList.get(0).getEmail(), uList.get(0).getRole());
    }

    public List<Users> getLakasUser(int id) {
        String sql = "SELECT DISTINCT users.id, users.felh, users.jelszo, users.emial, users.role FROM users INNER JOIN tartozik ON users.id=tartozik.felh_id INNER JOIN lakas ON tartozik.lakas_id=lakas.id WHERE lakas.lakasnev IN (SELECT lakas.lakasnev FROM lakas INNER JOIN tartozik ON tartozik.lakas_id=lakas.id WHERE tartozik.felh_id = "+id+")";
        return jdbc.query(sql,(rs,i)-> new Users(
                rs.getInt("id"),
                rs.getString("felh"),
                rs.getString("jelszo"),
                rs.getString("emial"),
                rs.getString("role")
        ));
    }

    public void deleteUserLakas(long id) {
    }
}
