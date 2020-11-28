package com.example.moviesapp.interfaces;

public interface IList {

    public interface View{
        void startFormActivity();
        void startSearchActivity();
        void startAppCRUDActivity();
    }

    public  interface  Presenter{
        void onClickAddMovie();
        void onClickSearchButton();
        void onClickAPPCRUD();
    }



}
