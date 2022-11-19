package com.okosotthon.controller;

import com.okosotthon.domain.Szoba;
import com.okosotthon.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
            @RequestParam("futesT") String futesT,
            Szoba szoba
    ){
            szobaService.addNewSzoba(szobaNev,terulet);
            eszkozokSzervice.addNewEszkoz(szobaNev,eszkozNev);
            futesService.addNewFutes(szobaNev, futesT);
        return "redirect:/meresek/szobak";
    }

    @GetMapping("/szobak/delete/{id}")
    public String deletSzoba(@PathVariable int id){
        szobaService.deleteSzoba(id);
        return "redirect:/meresek/szobak";
    }
    @GetMapping("/szobak/edit/{id}")
    public String editSzobaFrom(@PathVariable long id, Model model){
        Szoba szoba = szobaService.getSelectedSzoba(id).get(0);
        model.addAttribute("szobak", szoba);
        return "szobaEdit";
    }

    //TODO updatere kicserélni
    @PostMapping("/szobak/edit/{id}")
    public String editSzoba(Szoba szoba, BindingResult result){
        szobaService.addNewSzoba(szoba.getSzobanev(),szoba.getTerulet());
        eszkozokSzervice.addNewEszkoz(szoba.getSzobanev(),szoba.getEszkozok().getEszkoznev());
        futesService.addNewFutes(szoba.getSzobanev(),szoba.getFutes().getFutestipus());
        return "szoba-list";
    }

}
