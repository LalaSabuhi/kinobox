package com.example.kinobox.controller;

import com.example.kinobox.model.Users;
import com.example.kinobox.model.UsersType;
import com.example.kinobox.service.UsersService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UserController {
    private final UsersService usersService;

    public UserController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/register")
    public String register(Model model){
        List<UsersType> getAllTypes = usersService.getAll();
        model.addAttribute("getAllTypes", getAllTypes);
        model.addAttribute("user", new Users());
        return "register";
    }
}
