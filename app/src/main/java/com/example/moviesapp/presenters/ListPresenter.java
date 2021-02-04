package com.example.moviesapp.presenters;

import android.annotation.SuppressLint;
import android.util.Log;

import com.example.moviesapp.interfaces.IList;
import com.example.moviesapp.models.EntityFilm;
import com.example.moviesapp.models.FilmModel;

import java.util.ArrayList;

public class ListPresenter implements IList.Presenter {

    private final IList.View view;
    private static final String TAG = "presenters/ListPresenter";
    private final FilmModel filmModel = new FilmModel();

    public ListPresenter(IList.View view) {
        this.view=view;
    }

    @SuppressLint("LongLogTag")
    @Override
    public void onClickAddMovie() {
        Log.d(TAG,"Inside OnClickAddMovie");
        view.startFormActivity();
    }

    @SuppressLint("LongLogTag")
    public void onClickSearchButton(){
        Log.d(TAG, "Inside OnClickSearchButton");
        view.startSearchActivity();
    }

    @SuppressLint("LongLogTag")
    public void onClickAPPCRUD(){
        Log.d(TAG, "Inside OnClickAppCRUD");
        view.startAppCRUDActivity();
    }

    @SuppressLint("LongLogTag")
    public void onClickRecyclerViewItem(String id) {
        Log.d(TAG, "Inside onClickRecyclerViewItem");
        view.startFormActivity(id);

    }

    @SuppressLint("LongLogTag")
    @Override
    public void onSwipe(int position) {
        Log.d(TAG, "Inside onSwipe");
        view.onItemSwipe(position);
    }

    @SuppressLint("LongLogTag")
    @Override
    public void showToast() {
        Log.d(TAG, "Inside showToast");
        view.showDeleteToast();
    }

    @SuppressLint("LongLogTag")
    @Override
    public ArrayList<EntityFilm> getAllItemsSummarize() {
        Log.d(TAG, "Inside getAllItemsSummarize");
        return filmModel.getAllSummarize();
    }

    @Override
    public boolean insertOnBBDD(EntityFilm film) {
        boolean result = false;
        if (filmModel.insert(film)) {
            result = true;
        }
        return result;
    }

    @Override
    public boolean deleteFilm(EntityFilm film) {
        return filmModel.deleteFilm(film);
    }

    @Override
    public ArrayList<EntityFilm> getItemsByCriterion(String type, String content) {
        if (type.equals("genre")) {
            return FilmModel.getItemsByGenre(content);
        } else if (type.equals("title")) {
            return FilmModel.getItemsByTitle(content);
        } else if (type.equals("date")) {
            return FilmModel.getItemsByDate(content);
        }
        return null;
    }

    @Override
    public ArrayList<EntityFilm> getItemsByAllCriterions(String genre, String date, String title) {
        return FilmModel.getItemsByAllCriterions(genre, date, title);
    }

}
