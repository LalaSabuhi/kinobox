package com.example.kinobox.controller;

import com.example.kinobox.model.Film;
import com.example.kinobox.service.FilmService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

@Controller
@RequestMapping("/admin")
public class FilmController {

    private final FilmService filmService;

    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping("/")
    public String admin(){
        return "admin-dashboard";
    }

    @GetMapping("/video")
    public String video(){
        return "video";
    }

    @GetMapping("/addNewFilm")
    public String addFilms(Model model){
        model.addAttribute("film" , new Film());
        return "add-film";
    }

    @PostMapping("/addNew")
    public String addNew(Film film, Model model){
        film.setDeploymentHistory(new Date());
        model.addAttribute("film",film);
        Film savedFilm = filmService.addNew(film);
        return "redirect:/admin/";
    }
}
