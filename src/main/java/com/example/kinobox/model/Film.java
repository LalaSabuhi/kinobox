package com.example.kinobox.model;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name="film")
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    public String genre;
    private String country;
    private String yearOfPresentation;
    private String mainRole;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date deploymentHistory;
    private String filmTitle;
    private String filmDescription;
    @Column(nullable = true, length = 64)
    private String filmImage;

    @Column(nullable = true,length = 64)
    private String filmTrailer;

    public Film(){

    }
    public Film(int id, String genre, String country, String yearOfPresentation, String mainRole, Date deploymentHistory, String filmTitle, String filmDescription, String filmImage) {
        this.id = id;
        this.genre = genre;
        this.country = country;
        this.yearOfPresentation = yearOfPresentation;
        this.mainRole = mainRole;
        this.deploymentHistory = deploymentHistory;
        this.filmTitle = filmTitle;
        this.filmDescription = filmDescription;
        this.filmImage = filmImage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getYearOfPresentation() {
        return yearOfPresentation;
    }

    public void setYearOfPresentation(String yearOfPresentation) {
        this.yearOfPresentation = yearOfPresentation;
    }

    public String getMainRole() {
        return mainRole;
    }

    public void setMainRole(String mainRole) {
        this.mainRole = mainRole;
    }

    public Date getDeploymentHistory() {
        return deploymentHistory;
    }

    public void setDeploymentHistory(Date deploymentHistory) {
        this.deploymentHistory = deploymentHistory;
    }

    public String getFilmTitle() {
        return filmTitle;
    }

    public void setFilmTitle(String filmTitle) {
        this.filmTitle = filmTitle;
    }

    public String getFilmDescription() {
        return filmDescription;
    }

    public void setFilmDescription(String filmDescription) {
        this.filmDescription = filmDescription;
    }

    public String getFilmImage() {
        return filmImage;
    }

    public void setFilmImage(String filmImage) {
        this.filmImage = filmImage;
    }

    @Override
    public String toString() {
        return "Film{" +
                "id=" + id +
                ", genre='" + genre + '\'' +
                ", country='" + country + '\'' +
                ", yearOfPresentation='" + yearOfPresentation + '\'' +
                ", mainRole='" + mainRole + '\'' +
                ", deploymentHistory=" + deploymentHistory +
                ", filmTitle='" + filmTitle + '\'' +
                ", filmDescription='" + filmDescription + '\'' +
                ", filmImage='" + filmImage + '\'' +
                '}';
    }
}
