package com.okosotthon.service;

import com.okosotthon.repository.FutesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FutesService {
    private FutesRepository futesRepository;
    @Autowired
    public void setFutesRepository(FutesRepository futesRepository) {
        this.futesRepository = futesRepository;
    }


    public void addNewFutes(String szobaNev, String futesT) {
        futesRepository.save(szobaNev,futesT);
    }
}
