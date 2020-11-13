package com.example.moviesapp.interfaces;

public interface IForm {

    public interface View{
        void finishFormActivity();
    }

    public  interface  Presenter{
        void onClickSaveButton();
    }
}
