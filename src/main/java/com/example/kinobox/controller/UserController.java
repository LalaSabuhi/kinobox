package com.example.kinobox.controller;

import com.example.kinobox.model.Users;
import com.example.kinobox.model.UsersType;
import com.example.kinobox.service.UsersService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class UserController {
    private final UsersService usersService;

    public UserController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new Users());
        return "register";
    }
    @PostMapping("/register/new")
    public String registerUser( @Valid Users user, Model model) {
        Optional<Users> optionalUsers= usersService.getUserByEmail(user.getEmail());
        if(optionalUsers.isPresent()){
            model.addAttribute("error","email already exist");
            model.addAttribute("user", new Users());
            return "register";
        }
        usersService.registerNewUser(user);
        return "redirect:/";
    }


}
