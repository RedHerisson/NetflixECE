package com.Model.map;

import java.awt.image.BufferedImage;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Movie extends BdModel {

    @JsonProperty("title")
    private String title;

    @JsonProperty("poster")
    private BufferedImage thumbnail;

    private String filePath;

    @JsonProperty("Released")
    private LocalDate releaseDate;

    @JsonProperty("Runtim")
    private int length;

    @JsonProperty("Director")
    private Person director;

    @JsonProperty("Actors")
    private ArrayList<Person> actors;

    @JsonProperty("Genre")
    private ArrayList<String> type;

    @JsonProperty("Plot")
    private String summary;

    @JsonProperty("Trailer")
    private String teaserPath;

    private double rating;

    private boolean awarded;

    private int viewCount;

    private boolean promoted;

    public Movie(int id, String title, BufferedImage thumbnail, String filePath, LocalDate releaseDate, int length, Person director, ArrayList<Person> actors, ArrayList<String> type, String summary, String teaserPath, boolean awarded, int viewCount, double rating, boolean promoted) {
        super(id);
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
        this.promoted = promoted;
    }

    //Constructeur par copie
    public Movie(Movie movie) {
        super(movie.getId());
        this.id = movie.getId();
        this.title = movie.getTitle();
        this.thumbnail = movie.getThumbnail();
        this.filePath = movie.getFilePath();
        this.releaseDate = movie.getReleaseDate();
        this.length = movie.getLength();
        this.director = movie.getDirector();
        this.actors = movie.getActors();
        this.type = movie.getTypeArray();
        this.summary = movie.getSummary();
        this.teaserPath = movie.getTeaserPath();
        this.rating = movie.getRating();
        this.awarded = movie.isAwarded();
        this.viewCount = movie.getViewCount();
        this.promoted = movie.isPromoted();
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

    public void addActor(Person actor) {
        actors.add(actor);
    }

    public void removeActor(Person actor) {
        actors.remove(actor);
    }

    public ArrayList<String> getTypeArray() {
        return type;
    }
    public String getTypes() {
        String type = "";
        for (String s : this.type) {
            type += s + ", ";
        }
        // remove last ", "
        type = type.substring(0, type.length() - 2);
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

    public String getTableName() {
        return "Movie";
    }

    public boolean isPromoted() {
        return promoted;
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

        String[] types = type.split(", ");
        for (String s : types) {
            this.type.add(s);
        }
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

    public void setPromoted(boolean promoted) {
        this.promoted = promoted;
    }
}
