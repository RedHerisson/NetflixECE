package Model.map;

import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class Movie {

    private String title;

    private Image Tumbnail;

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

    public Movie(String title, Image tumbnail, String filePath, LocalDate releaseDate, int length, Person director, ArrayList<Person> actors, String type, String summary, String teaserPath, int rating, boolean awarded, int viewCount) {
        this.title = title;
        Tumbnail = tumbnail;
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
