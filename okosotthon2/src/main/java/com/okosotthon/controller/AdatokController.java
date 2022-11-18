package com.okosotthon.controller;

import com.okosotthon.domain.Szoba;
import com.okosotthon.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/meresek")
public class AdatokController {

    private SzobaService szobaService;
    private EszkozokSzervice eszkozokSzervice;
    private FutesService futesService;

    @Autowired
    public void setSzobaService(SzobaService szobaService) {
        this.szobaService = szobaService;
    }
    @Autowired
    public void setEszkozokSzervice(EszkozokSzervice eszkozokSzervice) {
        this.eszkozokSzervice = eszkozokSzervice;
    }
    @Autowired
    public void setFutesService(FutesService futesService) {
        this.futesService = futesService;
    }

    @GetMapping("/szobak")
    public String usersSzobakList(Szoba szoba, Model model){
        System.out.println("asdasdasdasdasd2");
        model.addAttribute("szobak", szobaService.getAllUserSzoba());
        return "szoba-list";
    }
    @PostMapping("/szobakAdd")
    public String addNewEszkoz(
            @RequestParam("szNev") String szobaNev,
            @RequestParam("eszNev") String eszkozNev,
            @RequestParam("terulete") int terulet,
            @RequestParam("futesT") String futesT
    ){
            szobaService.addNewSzoba(szobaNev,terulet);
            eszkozokSzervice.addNewEszkoz(szobaNev,eszkozNev);
            futesService.addNewFutes(szobaNev, futesT);
        return "redirect:/meresek/szobak";
    }
}
