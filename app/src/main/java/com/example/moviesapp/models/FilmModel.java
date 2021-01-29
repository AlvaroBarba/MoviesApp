package com.example.moviesapp.models;

import android.util.Log;

import java.util.ArrayList;
import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmResults;

public class FilmModel {

    private final String TAG = "models/FilmModel";

    public boolean insert(EntityFilm film) {

        film.setId(UUID.randomUUID().toString());
        Realm realm = Realm.getDefaultInstance();
        Log.d("Realm", "path: " + realm.getPath());

        try {
            realm.beginTransaction();
            realm.copyToRealm(film);
            realm.commitTransaction();
        } catch (Exception e) {
            return false;
        }

        realm.close();

        return true;
    }

    public ArrayList<EntityFilm> getAllSummarize() {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<EntityFilm> results = realm.where(EntityFilm.class).findAll();

        Log.d("Realm find items:", " " + results.size());

        ArrayList<EntityFilm> films = new ArrayList<>();
        films.addAll(realm.copyFromRealm(results));

        realm.close();

        ArrayList<EntityFilm> result = new ArrayList<>();

        for (EntityFilm a : films) {
            EntityFilm entityFilm = new EntityFilm();
            entityFilm.setId(a.getId());
            entityFilm.setTitle(a.getTitle());
            entityFilm.setDate(a.getDate());
            entityFilm.setPhoto(a.getPhoto());
            result.add(entityFilm);
        }

        return result;
    }

    public boolean deleteFilm(EntityFilm film) {
        boolean flag;
        Realm realm = Realm.getDefaultInstance();
        try {
            realm.beginTransaction();
            EntityFilm entityFilm = realm.where(EntityFilm.class).equalTo("id", film.getId()).findFirst();
            entityFilm.deleteFromRealm();
            realm.commitTransaction();
            flag = true;
        } catch (Exception e) {
            flag = false;
        }
        realm.close();

        return flag;
    }

    public boolean updateFilm(EntityFilm film) {
        boolean flag = false;
        Realm realm = Realm.getDefaultInstance();
        try {
            realm.beginTransaction();
            EntityFilm entityFilm = realm.where(EntityFilm.class).equalTo("id", film.getId()).findFirst();
            entityFilm.setPhoto(film.getPhoto());
            entityFilm.setDate(film.getDate());
            entityFilm.setTitle(film.getTitle());
            entityFilm.setGenre(film.getGenre());
            entityFilm.setRate(film.getRate());
            entityFilm.setSynopsis(film.getSynopsis());
            entityFilm.setDirector(film.getDirector());
            entityFilm.setWatched(film.isWatched());
            realm.insertOrUpdate(entityFilm);
            realm.commitTransaction();
            flag = true;
        } catch (Exception e) {
            Log.d(TAG, "Error " + e);
        }

        return flag;
    }
/*
    public String[] getAllGenres(){
    }

 */
}
