package com.okosotthon.controller;

import com.okosotthon.domain.Users;
import com.okosotthon.service.LakasService;
import com.okosotthon.service.SzobaService;
import com.okosotthon.service.TartozikService;
import com.okosotthon.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


//@Slf4j
@Controller
@RequestMapping("/users")
public class UserController {
    private UserService userService;
    private SzobaService szobaServic;
    private LakasService lakasService;
    private TartozikService tartozikService;
    private UserDetailsService userDetailsService;

    @Autowired
    public void setSzobaServic(SzobaService szobaServic) {
        this.szobaServic = szobaServic;
    }
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    @Autowired
    public void setLakasService(LakasService lakasService) {
        this.lakasService = lakasService;
    }
    @Autowired
    public void setTartozikService(TartozikService tartozikService) {
        this.tartozikService = tartozikService;
    }
    @Autowired
    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @GetMapping("/")
    public String indexForm(){
        return "index";
    }

    //TODO megkérdezni hogy ez most hogy is működik
    @GetMapping("/login")
    public String loginForm(Users users,Model model){
        System.out.println("get user" + users.getFelh());
        model.addAttribute("users",new Users());
        return "bejelentkezes";
    }

//    @PostMapping("/logged")
//    public String userLoggedIn(Users users){
//        return users != null ? "loggedIn" : "loggedOut";
//    }

    //region Adatok megszerzése az inputból
    @PostMapping("/loginUser")
    public String loginUser(Users users, Model model){
        System.out.println(users.getFelh()+ " post");
        model.addAttribute("users",new Users());
        users.setRole("USER");
        userDetailsService.loadUserByUsername(users.getFelh());
        userService.setActualUser(users);
        return "redirect:/szobak/monitor";
    }
    //endregion

    //region Regisztráció oldalra továbbítás
    @GetMapping("/regiszt")
    public String register(Model model){
        model.addAttribute("users", new Users());
        return "regisztracio";
    }
    @PostMapping("/regiszt_process")
    public String regisztUser(Users users){
        BCryptPasswordEncoder pwdEncoder = new BCryptPasswordEncoder();
        String encodedPassword = pwdEncoder.encode(users.getJelszo());
        users.setJelszo(encodedPassword);

        userService.saveUser(users);
        return "redirect:/login";
    }
//    @GetMapping("/regiszt")
//    public String register(Users users){
//        return "regisztracio";
//    }
//    //endregion
//
//    @PostMapping("/regiszt")
//    public String registerUser(Users users, @RequestParam("pwd2") String pwd2){
//        System.out.println("Felhasznalonev es jelszo: "+ users.getFelh()+" " + users.getJelszo());
//        if(users.getJelszo().equals(pwd2)){
//            userService.userRegister(users);
//        }else {
//            return "redirect:/users/index";
//        }
//        return "redirect:/users/login";
//    }


    @GetMapping("/user-data")
    public String userModify(Users users, Model model){
        model.addAttribute("users", userService.getActualUserList());
        return "user-details";
    }

    @PostMapping("/changePwd")
    public String changeUserPwd(Users users,
                                @RequestParam("oldpwd") String oldpwd,
                                @RequestParam("newpwd1") String newpwd1,
                                @RequestParam("newpwd2") String newpwd2,
                                @RequestParam("newname") String newname,
                                @RequestParam("newemail") String newemail){
                userService.updateUserData(oldpwd,newpwd1,newpwd2, newname, newemail);
        return "user-details";
    }

    @PostMapping("newLakas")
    public String addNewLakas(@RequestParam("lakasNev") String lakasnev){
        lakasService.addNewLakas(lakasnev);
        tartozikService.addNewTartozik(lakasnev);
        return "user-details";
    }
}
