package com.example.kinobox.service;

import com.example.kinobox.model.Film;
import com.example.kinobox.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmService {
    private final FilmRepository filmRepository;

    @Autowired
    public FilmService(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    public Film addNew(Film film){
        return filmRepository.save(film);
    }
    public List<Film>  listFilm(){
        return filmRepository.findAll();
    }

    public Film getOne(int id) {
        return filmRepository.findById(id).orElseThrow(()-> new RuntimeException("Film could not found"));
    }

    public void delete(int id) {
        filmRepository.deleteById(id);
    }
    public Film saveFilm(Film film){
        return filmRepository.save(film);
    }


}
