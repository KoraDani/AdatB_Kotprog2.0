package com.okosotthon.service;

import com.okosotthon.domain.Users;
import com.okosotthon.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private Users actualUser;

    public Users getActualUser() {
        return actualUser;
    }
    public List<Users> getActualUserList() {
        List<Users> users = new ArrayList<>();
        users.add(actualUser);
        return users;
    }
    public List<Users> getLakasUser() {
        return usersRepository.getLakasUser(actualUser.getId());
    }
    public int getActualUserId() {
        return actualUser.getId();
    }

    private UsersRepository usersRepository;

    @Autowired
    public void setUsersRepository(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    //region Bejelentkezett felhasználó beállítása
    public void setActualUser(Users u){
        List<Users> uList = usersRepository.getAllUsers();
        String jelszo = passwordHash(u.getJelszo());
        for (int i= 0; i < uList.size(); i++){
            if(u.getFelh().equals(uList.get(i).getFelh())){
                actualUser = new Users(uList.get(i).getId(),uList.get(i).getFelh(),uList.get(i).getJelszo(),uList.get(i).getEmail(),uList.get(0).getRole());
            }
        }
    }
    //endregion


    //region Jelszo Hash
    public String passwordHash(String jelszo){
        String hash = "";
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] messageDigest = md.digest(jelszo.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            hash = no.toString(16);
            return hash.toUpperCase();
         }catch (NoSuchAlgorithmException e){
            throw new RuntimeException(e);
        }
    }
    //endregion

    public Boolean UserPwdEqual(String oldpwd){
        //System.out.println("Hashelt regi jelszo: "+passwordHash(oldpwd) );
        //System.out.println("Aktualis jelszo: " + getActualUser().getJelszo());
        return passwordHash(oldpwd).equals(getActualUser().getJelszo());
    }

    public void updateUserData(String oldpwd, String newpwd1, String newpwd2, String newname, String newemail) {
        if((newname != null & !newname.equals("")) || (newemail != null && !newemail.equals(""))){
            usersRepository.updateUserData(newname, newemail, actualUser.getId());
        }if(oldpwd != null && !oldpwd.equals("") && passwordHash(oldpwd).equals(actualUser.getJelszo())){
            if(newpwd1.equals(newpwd2)){
                usersRepository.updateUserPassword(passwordHash(newpwd1), actualUser.getId());
            }
        }
    }

    public void userRegister(Users users) {
        users.setJelszo(passwordHash(users.getJelszo()));
        usersRepository.save(users);
    }

    public void saveUser(Users users) {
        usersRepository.save(users);
    }

    public void deleteUserLakas(long id) {
        usersRepository.deleteUserLakas(id);
    }
}
