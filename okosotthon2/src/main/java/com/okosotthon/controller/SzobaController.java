package com.okosotthon.controller;

import com.okosotthon.domain.Users;
import com.okosotthon.service.EszkozokSzervice;
import com.okosotthon.service.SzobaService;
import com.okosotthon.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/szobak")
public class SzobaController {

    private SzobaService szobaService;
    private UserService userService;
    private EszkozokSzervice eszkozokSzervice;

    //region Service
    @Autowired
    public void setSzobaService(SzobaService szobaService) {
        this.szobaService = szobaService;
    }
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    @Autowired
    public void setEszkozokSzervice(EszkozokSzervice eszkozokSzervice) {
        this.eszkozokSzervice = eszkozokSzervice;
    }
    //endregion


    @PostMapping("/logged")
    public String userLoggedIn(Users users) {
        return users != null ? "fragments/LoggedIn" : "fragments/notLoggedIn";
    }

        //region Bejelentkezett felhasználó szobáinak kiiratása
    @GetMapping("/monitor")
    public String listSzoba(Model model){
        int lid = userService.getActualUserId();
        System.out.println(lid + " lakasid");
        model.addAttribute("szobak", szobaService.getAllUserSzobaAdat(lid));
        return "monitor";
    }
    //endregion
}
