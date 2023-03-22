package com.Model.map;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.time.LocalDate;
import java.util.ArrayList;

public class Movie {

    private int id;
    private String title;

    private BufferedImage thumbnail;

    private String filePath;

    private LocalDate releaseDate;

    // length in second
    private int length;

    private Person director;

    private ArrayList<Person> actors;

    private String type;

    private String summary;

    private String teaserPath;

    private int rating;

    private boolean awarded;

    private int viewCount;

    public Movie(int ID, String title, BufferedImage thumbnail, String filePath, LocalDate releaseDate, int length, Person director, ArrayList<Person> actors, String type, String summary, String teaserPath, int rating, boolean awarded, int viewCount) {
        this.id = id;
        this.title = title;
        this.thumbnail = thumbnail;
        this.filePath = filePath;
        this.releaseDate = releaseDate;
        this.length = length;
        this.director = director;
        this.actors = actors;
        this.type = type;
        this.summary = summary;
        this.teaserPath = teaserPath;
        this.rating = rating;
        this.awarded = awarded;
        this.viewCount = viewCount;
    }
}
