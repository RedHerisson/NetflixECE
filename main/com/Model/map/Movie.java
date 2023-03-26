package com.Model.map;

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

    private double rating;

    private boolean awarded;

    private int viewCount;

    public Movie(int id, String title, BufferedImage thumbnail, String filePath, LocalDate releaseDate, int length, Person director, ArrayList<Person> actors, String type, String summary, String teaserPath, boolean awarded, int viewCount, double rating) {
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

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public BufferedImage getThumbnail() {
        return thumbnail;
    }

    public String getFilePath() {
        return filePath;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public int getLength() {
        return length;
    }

    public Person getDirector() {
        return director;
    }

    public ArrayList<Person> getActors() {
        return actors;
    }

    public String getType() {
        return type;
    }

    public String getSummary() {
        return summary;
    }

    public String getTeaserPath() {
        return teaserPath;
    }

    public double getRating() {
        return rating;
    }

    public boolean isAwarded() {
        return awarded;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setThumbnail(BufferedImage thumbnail) {
        this.thumbnail = thumbnail;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setDirector(Person director) {
        this.director = director;
    }

    public void setActors(ArrayList<Person> actors) {
        this.actors = actors;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setTeaserPath(String teaserPath) {
        this.teaserPath = teaserPath;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setAwarded(boolean awarded) {
        this.awarded = awarded;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }
}
