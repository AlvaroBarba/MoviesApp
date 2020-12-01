package com.example.moviesapp.interfaces;

public interface ISearch {

    public interface View{
        void showDatePicker();
        void finishSearchActivity();
    }

    public interface Presenter{
        void onClickDateButton();
        void onClickSearchButton();
    }
}
