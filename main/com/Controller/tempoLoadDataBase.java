package com.Controller;


import com.Model.dao.MovieAccessor;
import com.Model.map.Movie;
import com.Model.map.Person;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class tempoLoadDataBase {

    MovieAccessor movieAccessor = new MovieAccessor();

    public tempoLoadDataBase() throws SQLException, ClassNotFoundException {
    }

    public static  String getJsonFromURL(String urlString) throws IOException {
        URL url;

        try {
            url = new URL(urlString);
            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url(url)
                    .build();

            try (Response response = client.newCall(request).execute()) {

                String json = response.body().string();
                //System.out.println(json);

                return json;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static String OMDBGetById(String id) throws IOException {
            //complete for id
            String apikkey = "9469cce6";
            return getJsonFromURL("https://www.omdbapi.com/?apikey=" + apikkey + "&i=" + id);
    }

    public static Movie JsonToMovie(String json) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> jsonMap = objectMapper.readValue(json, new TypeReference<Map<String, Object>>(){});

        String title = (String) jsonMap.get("Title");

        String StrReleaseDate = (String) jsonMap.get("Released");
        //System.out.println("Input string: " + StrReleaseDate);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.ENGLISH);
        LocalDate releaseDate = LocalDate.parse(StrReleaseDate, formatter);




        int Runtime = Integer.parseInt((String) jsonMap.get("Runtime").toString().split(" ")[0]);
        String type = (String) jsonMap.get("Genre");
        String directorInString = (String) jsonMap.get("Director");
        String dirName = directorInString.split(" ")[0];
        String dirSurname = directorInString.split(" ")[1];
        Person director = new Person(-1, dirName, dirSurname, 30, "M");
        String[] actorsInString = ((String) jsonMap.get("Actors")).split(", ");
        ArrayList<Person> actors = new ArrayList<>();
        for(String actor : actorsInString){
            String actorName = actor.split(" ")[0];
            String actorSurname = actor.split(" ")[1];
            actors.add( new Person(-1, actorName, actorSurname, 30, "M") );
        }
        String posterPath = (String) jsonMap.get("Poster");
        BufferedImage image = ImageIO.read(new URL(posterPath));
        boolean Awarded = false;

        String AwardDesc = (String) jsonMap.get("Awards");

        if(AwardDesc.contains("Oscar".toLowerCase()) || AwardDesc.contains("Oscars".toLowerCase() ) ) {
            Awarded = true;
        }

        // test if image is loaded
        //ImageIO.write(image, "jpg", new File("/resources/images/test.jpg"));
        String plot = (String) jsonMap.get("Plot");
        String videoPath = "/resources/video/" + (String) jsonMap.get("imdbID");


        Movie movie = new Movie(-1, title, image, videoPath, releaseDate, Runtime, director, actors, type, plot,
                videoPath, Awarded, 0, 0);
        return movie;
    }

    public static String getYtLinkFromImdbId(String imdbId) throws IOException {
        String json1 = getJsonFromURL("https://api.themoviedb.org/3/movie/" + imdbId + "/external_ids?api_key=437f7c220247b94e83186d3692270200");

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> jsonMap = objectMapper.readValue(json1, new TypeReference<Map<String, Object>>(){});

        String localId = jsonMap.get("id").toString();

        String json2 = getJsonFromURL("https://api.themoviedb.org/3/movie/" + localId +"/videos?api_key=437f7c220247b94e83186d3692270200&language=en-US");

        ObjectMapper objectMapper2 = new ObjectMapper();

        JsonNode node = objectMapper2.readTree(json2);

// get the first result object
        JsonNode result = node.get("results").get(0);

// get the key value
        String ytKey = result.get("key").asText();

        String ytLink = ("https://www.youtube.com/watch?v=" + ytKey );

        return ytLink;
    }

    public static ArrayList<String> readImdbFromFile(String path, int startLigne) {
        ArrayList<String> imdbIds = new ArrayList<>();
        try {
            //
            File file = new File(path);
            Scanner sc = new Scanner(file);
            for(int i = 0; i < startLigne; i++){
                sc.nextLine();
            }
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                imdbIds.add(line);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return imdbIds;
    }

    public static void addYtURLToFile( String ytURL ) {
        try {
            FileWriter myWriter = new FileWriter("ytURL.txt", true);
            myWriter.write(ytURL + "\n");
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        try {
            int startLigne = 510;
            // read a csv file with all the movies ids
            ArrayList<String> imdbIds = readImdbFromFile("imdb_ID.csv", startLigne);
            MovieAccessor movieAccessor = new MovieAccessor();
            int i = startLigne;
            for( String imdbId : imdbIds){
                i++;
                // get the json from omdb
                String json = OMDBGetById(imdbId);
                // convert the json to a movie object
                Movie movie = JsonToMovie(json);
                // get the youtube link
                String ytLink = getYtLinkFromImdbId(imdbId);
                System.out.println(ytLink);
                System.out.println("Movie " + i + "/" + 549 + " added to the database");
                addYtURLToFile( ytLink + "," + imdbId);

                //add the movie to the database
                movieAccessor.create(movie);

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
