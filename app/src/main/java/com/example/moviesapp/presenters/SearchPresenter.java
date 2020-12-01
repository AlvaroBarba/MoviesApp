package com.example.moviesapp.presenters;

import com.example.moviesapp.interfaces.ISearch;

public class SearchPresenter implements ISearch.Presenter {

    private ISearch.View view;
    private static final String TAG  = "presenters/SearchPresenter";

    public SearchPresenter(ISearch.View view) {this.view=view;}

    @Override
    public void onClickDateButton() {
        view.showDatePicker();
    }

    @Override
    public void onClickSearchButton() {
        view.finishSearchActivity();
    }
}
