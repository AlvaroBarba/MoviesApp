package com.example.moviesapp.interfaces;

public interface IList {

    public interface View{
        void startFormActivity();
    }

    public  interface  Presenter{
        void onClickAddMovie();
    }

}
