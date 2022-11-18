package com.okosotthon.service;

import com.okosotthon.repository.TartozikRepository;
import com.okosotthon.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TartozikService {
    private TartozikRepository tartozikRepository;
    private UserService userService;

    @Autowired
    public void setTartozikRepository(TartozikRepository tartozikRepository) {
        this.tartozikRepository = tartozikRepository;
    }
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    public void addNewTartozik(String lakasnev) {
        tartozikRepository.save(userService.getActualUserId(),lakasnev);
    }


}
