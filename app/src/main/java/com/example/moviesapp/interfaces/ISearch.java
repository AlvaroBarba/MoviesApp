package com.example.moviesapp.interfaces;

import java.util.ArrayList;

public interface ISearch {

    interface View {
        void showDatePicker();

        void finishSearchActivity();
    }

    interface Presenter {
        void onClickDateButton();

        void onClickSearchButton();

        ArrayList<String> getAllGenres();

    }
}
