package com.example.kinobox.controller;

import com.example.kinobox.model.Film;
import com.example.kinobox.service.FilmService;
import com.example.kinobox.util.FileUploadUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/admin")
public class FilmController {

    private final FilmService filmService;

    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping("/")
    public String adminHome(Model model){
        List<Film> listFilms = filmService.listFilm();
        model.addAttribute("listFilms", listFilms);
        return "admin-dashboard";
    }

    @GetMapping("/addNewFilm")
    public String addFilms(Model model){
        model.addAttribute("film", new Film());
        return "add-film";
    }

    @PostMapping("/addNew")
    public String addNew(Film film, Model model, @RequestParam("image") MultipartFile multipartImageFile, @RequestParam("video") MultipartFile multipartVideoFile){
        film.setDeploymentHistory(new Date());
        model.addAttribute("film", film);

        String imageFilename = "";
        String videoFilename = "";

        if (!multipartImageFile.isEmpty()) {
            imageFilename = StringUtils.cleanPath(Objects.requireNonNull(multipartImageFile.getOriginalFilename()));
            film.setFilmImage(imageFilename);
        }

        if (!multipartVideoFile.isEmpty()) {
            videoFilename = StringUtils.cleanPath(Objects.requireNonNull(multipartVideoFile.getOriginalFilename()));
            film.setFilmTrailer(videoFilename);
        }

        Film savedFilm = filmService.addNew(film);

        String uploadDir = "images/films/" + savedFilm.getId();
        try {
            FileUploadUtil.saveFile(uploadDir, imageFilename, multipartImageFile);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        String uploadDir1 = "videos/films/" + savedFilm.getId();
        try {
            FileUploadUtil.saveFile(uploadDir1, videoFilename, multipartVideoFile);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return "redirect:/admin/";
    }
    @GetMapping("/delete/{id}")
    public String deleteFilm(@PathVariable("id") int id) {
        filmService.delete(id);
        return "redirect:/admin/";
    }
    @PostMapping("admin/edit/{id}")
    public String editFilm(@PathVariable("id") int id, Model model) {
        Film film = filmService.getOne(id);
        model.addAttribute("film", film);
        return "add-film";
    }



}
