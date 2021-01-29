package com.example.moviesapp.models;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class EntityFilm extends RealmObject {

    @PrimaryKey
    private String id;
    private String title;
    private String director;
    private String synopsis;
    private String date;
    private String rate;
    private String genre;
    private String photo;
    private boolean watched;

    public boolean isWatched() {
        return watched;
    }

    public void setWatched(boolean watched) {
        this.watched = watched;
    }

    public EntityFilm() {
    }

    public String getTitle() {
        return title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Integer setTitle(String title) {
        int err = 0;
        if(!title.isEmpty()){
            Pattern p = Pattern.compile("[a-zA-ZñÑ ]");
            Matcher m = p.matcher(title);
            if(!m.find()){
                err = 2; //Only letters
            }else{
                this.title = title.toLowerCase();
            }
        }else{
            err = 1; //Empty field
        }
        return err;
    }

    public String getDirector() {
        return director;
    }

    public Integer setDirector(String director) {
        int err = 0;
        if(!director.isEmpty()){
            Pattern p = Pattern.compile("[a-zA-ZñÑ ]");
            Matcher m = p.matcher(director);
            if(!m.find()){
                err = 2; //Only letters
            }else{
                this.director = director.toLowerCase();
            }
        }else{
            err = 1; //Empty field
        }
        return err;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public Integer setSynopsis(String synopsis) {
        int err = 0;
        if(!synopsis.isEmpty()){
            Pattern p = Pattern.compile("[a-zA-Z0-9ñÑ ]");
            Matcher m = p.matcher(synopsis);
            if(!m.find()){
                err = 2; //Only letters and numbers
            }else{
                this.synopsis = synopsis.toLowerCase();
            }
        }else{
            err = 1; //Empty field
        }
        return err;
    }

    public String getDate() {
        return date;
    }

    public Integer setDate(String date) {
        int err = 0;
        if (!date.isEmpty()) {
            Pattern p = Pattern.compile("^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)" +
                    "(\\/|-|\\.)(?:0?[13-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)" +
                    "0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|" +
                    "(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)" +
                    "(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$");
            Matcher m = p.matcher(date);
            if(!m.find()){
                err = 2; //Date not correct
            }else{
                this.date=date;
            }
        }else{
            err = 1; //empty field
        }
        return err;
    }

    public String getRate() {
        return rate;
    }

    public Integer setRate(String rate) {
        int err = 0;
        if(!rate.isEmpty()){
            Pattern p = Pattern.compile("[0-5]");
            Matcher m = p.matcher(rate);
            if(!m.find()){
                err = 2; //No numbers into 0 and 5 both included
            }else{
                this.rate = rate;
            }
        }else{
            err = 1; //Empty field
        }
        return err;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

}
