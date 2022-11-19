package com.okosotthon.service;

import com.okosotthon.domain.Lakas;
import com.okosotthon.repository.LakasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LakasService {
    private LakasRepository lakasRepository;

    @Autowired
    public void setLakasRepository(LakasRepository lakasRepository) {
        this.lakasRepository = lakasRepository;
    }

    public void addNewLakas(String lakasnev) {
        lakasRepository.save(lakasnev);
    }

    public List<Lakas> getUserLakas(int userId) {
        return lakasRepository.getAllUserLakas(userId);
    }
}
