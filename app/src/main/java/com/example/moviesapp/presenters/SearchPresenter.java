package com.example.moviesapp.presenters;

import com.example.moviesapp.interfaces.ISearch;
import com.example.moviesapp.models.FilmModel;

import java.util.ArrayList;

public class SearchPresenter implements ISearch.Presenter {

    private final ISearch.View view;
    private static final String TAG = "presenters/SearchPresenter";
    private final FilmModel model = new FilmModel();

    public SearchPresenter(ISearch.View view) {this.view=view;}

    @Override
    public void onClickDateButton() {
        view.showDatePicker();
    }

    @Override
    public void onClickSearchButton() {
        view.finishSearchActivity();
    }

    @Override
    public ArrayList<String> getAllGenres() {
        return model.getAllGenres();
    }
}
