package com.okosotthon.service;

import com.okosotthon.domain.Adatok;
import com.okosotthon.domain.Eszkozok;
import com.okosotthon.domain.Szoba;
import com.okosotthon.repository.AdatokRepository;
import com.okosotthon.repository.EszkozRepository;
import com.okosotthon.repository.SzobaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class SzobaService {
    private SzobaRepository szobaRepository;
    private AdatokService adatokService;
    private EszkozRepository eszkozRepository;
    private UserService userService;
    private int felhasznaloid = 0;

    @Autowired
    public void setAdatokService(AdatokService adatokService) {
        this.adatokService = adatokService;
    }
    @Autowired
    public void setSzobaRepository(SzobaRepository szobaRepository) {
        this.szobaRepository = szobaRepository;
    }
    @Autowired
    public void setEszkozRepository(EszkozRepository eszkozRepository) {
        this.eszkozRepository = eszkozRepository;
    }
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public List<Szoba> getAllSzoba(){
        return szobaRepository.getAllSzoba();
    }

    //region Felhasználó Szobái
    /*getUserSzoba vár egy lakasId int értéket és ez alapján kiszűri
    * hogy a felhasználóhoz milyen szobák tartoznak.
    * Egy olyab listával tér vissza amiben csak a bejelentkezett felhasználó
    * szobái vannak*/
    /*public List<Szoba> getUserSzoba(int felhid){
        felhasznaloid = felhid;
        List<Szoba> szoba = szobaRepository.getAllUserSzoba(felhid);
        List<Szoba> ujszoba =new ArrayList<>();
        for (int i = 0; i < szoba.size(); i++){
            ujszoba.add(new Szoba(
                    szobaRepository.getAllUserSzobaAdat(szoba.get(i).getId()).get(i).getSzobanev(),
                    szobaRepository.getAllUserSzobaAdat(szoba.get(i).getId()).get(i).getAdatok().getHomerseklet(),
                    szobaRepository.getAllUserSzobaAdat(szoba.get(i).getId()).get(i).getAdatok().getParatartalom()
                    //(eszkozRepository.getAllUserEszkoz(szoba.get(i).getId()) == null ? new Eszkozok("") : eszkozRepository.getAllUserEszkoz(szoba.get(i).getId()))
                    )
            );
        }
        return ujszoba;
    }*/
    //endregion

    public List<Szoba> getAllUserSzoba(){
        System.out.println("Felhasznalo id: " + userService.getActualUserId());
       return szobaRepository.getAllUserSzoba(userService.getActualUserId());
    }

    public List<Szoba> getAllUserSzobaAdat(int lakasid){
        return szobaRepository.getAllUserSzobaAdat(lakasid);
    }

    public Boolean addNewSzoba(String szobaNev, int terulet) {
        if(szobaRepository.save(userService.getActualUserId(),szobaNev,terulet) == 1){
            return true;
        }else {
            return false;
        }
    }
}
