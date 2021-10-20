package com.ua.pedpresa.pedpresa_form.controller;

import com.ua.pedpresa.pedpresa_form.domain.Role;
import com.ua.pedpresa.pedpresa_form.domain.User;
import com.ua.pedpresa.pedpresa_form.repos.UserRepo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {
    private final UserRepo userRepo;

    public RegistrationController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String,Object> model){
        User userFromDb = userRepo.findByUsername(user.getUsername());
        if(userFromDb != null) {
            model.put("message", "User exist!");
            return "registration";
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepo.save(user);


        return "redirect:/login";
    }
}
