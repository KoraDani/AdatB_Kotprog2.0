package com.okosotthon.service;

import com.okosotthon.domain.Eszkozok;
import com.okosotthon.repository.EszkozRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EszkozokSzervice {

    private EszkozRepository eszkozRepository;

    @Autowired
    public void setEszkozRepository(EszkozRepository eszkozRepository) {
        this.eszkozRepository = eszkozRepository;
    }

    public Eszkozok getAllUserEszkoz(int szobaid) {
        return eszkozRepository.getAllUserEszkoz(szobaid);
    }

    public Boolean addNewEszkoz(String szobaNev,String eszkozNev) {
        if (eszkozRepository.addNewEszkoz(szobaNev,eszkozNev) == 1){
            return true;
        }else {
            return false;
        }

    }
}
