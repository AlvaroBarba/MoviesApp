package com.example.moviesapp.models;

import android.util.Log;

import java.util.ArrayList;
import java.util.Objects;
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

    public static ArrayList<EntityFilm> getItemsByGenre(String genre) {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<EntityFilm> results = realm.where(EntityFilm.class).contains("genre", genre).findAll();
        ArrayList<EntityFilm> result = new ArrayList<>();
        result.addAll(realm.copyFromRealm(results));
        return result;
    }

    public static ArrayList<EntityFilm> getItemsByTitle(String title) {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<EntityFilm> results = realm.where(EntityFilm.class).contains("title", title).findAll();
        ArrayList<EntityFilm> result = new ArrayList<>();
        result.addAll(realm.copyFromRealm(results));
        return result;
    }

    public static ArrayList<EntityFilm> getItemsByDate(String date) {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<EntityFilm> results = realm.where(EntityFilm.class).contains("date", date).findAll();
        ArrayList<EntityFilm> result = new ArrayList<>();
        result.addAll(realm.copyFromRealm(results));
        return result;
    }

    public static ArrayList<EntityFilm> getItemsByAllCriterions(String genre, String date, String title) {
        Realm realm = Realm.getDefaultInstance();
        ArrayList<EntityFilm> result = new ArrayList<>();
        RealmResults<EntityFilm> results = realm.where(EntityFilm.class).contains("title", title).and().equalTo("date", date).and().equalTo("genre", genre).findAll();
        result.addAll(realm.copyFromRealm(results));
        realm.close();
        return result;
    }

    public boolean updateFilm(EntityFilm film) {
        boolean flag = false;
        Realm realm = Realm.getDefaultInstance();
        try {
            realm.beginTransaction();
            realm.copyToRealmOrUpdate(film);
            realm.commitTransaction();
            flag = true;
        } catch (Exception e) {
        }

        return flag;
    }

    public ArrayList<String> getAllGenres() {
        ArrayList<String> result = new ArrayList<>();
        Realm realm = Realm.getDefaultInstance();
        RealmResults<EntityFilm> results = realm.where(EntityFilm.class).distinct("genre").findAll();
        if (results != null) {
            ArrayList<EntityFilm> films = (ArrayList<EntityFilm>) realm.copyFromRealm(results);
            for (EntityFilm f : films) {
                if (f.getGenre() != null) {
                    result.add(f.getGenre());
                }
            }
        }
        realm.close();
        return result;
    }

    public EntityFilm getById(String id) {
        Realm realm = Realm.getDefaultInstance();
        EntityFilm result = realm.copyFromRealm(Objects.requireNonNull(realm.where(EntityFilm.class).equalTo("id", id).findFirst()));
        realm.close();
        return result;
    }
}
