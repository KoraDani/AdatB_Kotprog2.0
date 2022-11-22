package com.okosotthon.service;

import com.okosotthon.domain.Futes;
import com.okosotthon.repository.FutesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public void updateNewFutes(long id, String futestipus) {
        futesRepository.update(id, futestipus);
    }

    public List<Futes> getAllUserFutes(int id) {
        return futesRepository.getAllUserFutes(id);
    }
}
