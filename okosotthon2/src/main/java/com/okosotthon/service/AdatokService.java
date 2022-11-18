package com.okosotthon.service;

import com.okosotthon.domain.Adatok;
import com.okosotthon.repository.AdatokRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdatokService {
    private AdatokRepository adatokRepository;

    @Autowired
    public void setAdatokRepository(AdatokRepository adatokRepository) {
        this.adatokRepository = adatokRepository;
    }

    public Adatok getSzobaAdatok(long szobaID){
        Adatok uj = new Adatok();
        List<Adatok> adatokList = adatokRepository.getSzobaAdatok(szobaID);
        for (int i = 0; i < adatokList.size(); i++ ){
            uj = new Adatok(
                    adatokList.get(i).getId(),
                    adatokList.get(i).getSzobaid(),
                    adatokList.get(i).getHomerseklet(),
                    adatokList.get(i).getParatartalom(),
                    adatokList.get(i).getDatum()
                    );
        }
        return uj;
    }
}
