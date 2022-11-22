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
    private UserService userService;
    private SzobaService szobaService;
    private EszkozokSzervice eszkozokSzervice;
    private FutesService futesService;
    private AdatokService adatokService;
    private LakasService lakasService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
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
    @Autowired
    public void setAdatokService(AdatokService adatokService) {
        this.adatokService = adatokService;
    }
    @Autowired
    public void setLakasService(LakasService lakasService) {
        this.lakasService = lakasService;
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
        model.addAttribute("szoba", szoba);
        return "szobaEdit";
    }

    @PostMapping("/szobak/edit/{id}")
    public String editSzoba(@PathVariable long id,Szoba szoba, BindingResult result){
        System.out.println("update szoba.get id " +szoba.getId());
        System.out.println("update szoba id " + id);
        szobaService.updateNewSzoba(id,szoba.getSzobanev(),szoba.getTerulet());
        eszkozokSzervice.updateNewEszkoz(id,szoba.getEszkozok().getEszkoznev());
        futesService.updateNewFutes(id,szoba.getFutes().getFutestipus());
        return "redirect:/meresek/szobak";
    }

    @GetMapping("/reszletesebb")
    public String reszletesAdatok(Model model){
        int id = userService.getActualUserId();
        model.addAttribute("szobak", szobaService.getAllUserSzoba());
        model.addAttribute("eszkozok",eszkozokSzervice.getAllUserEszkoz(id));
        model.addAttribute("futes", futesService.getAllUserFutes(id));
        model.addAttribute("lakas",lakasService.getUserLakas(id));
        model.addAttribute("adatok",adatokService.getAllUserAdatok(id));
        return "reszletes";
    }

}
