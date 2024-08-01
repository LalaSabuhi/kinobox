package com.example.kinobox.controller;

import com.example.kinobox.model.Film;
import com.example.kinobox.service.FilmService;
import com.example.kinobox.service.UsersService;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {
    private final FilmService filmService;

    public HomeController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping
    public String home(Model model){
        List<Film> listFilms = filmService.listFilm();
        model.addAttribute("listFilms", listFilms);
        UsersService usersService = null;
        Object currentUserProfile = usersService.getCurrentUserProfile();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUsername = authentication.getName();
            model.addAttribute("username", currentUsername);
        }

        model.addAttribute("user", currentUserProfile);

        return "index";
    }
    @GetMapping("/film-details/{id}")
    public String display(@PathVariable("id")int id, Model model){
        Film filmDetails = filmService.getOne(id);
        System.out.println(filmDetails.getVideoPath());
        model.addAttribute("filmDetails", filmDetails);
        return "film-details";
    }
    @GetMapping("/login")
    public String login(){
        return "login";
    }


}
