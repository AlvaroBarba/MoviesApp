package com.example.moviesapp.interfaces;

public interface IForm {

    public interface View{
        void finishFormActivity();
        void showDatePicker();
        void onClickAddImage();
    }

    public  interface  Presenter{
        void onClickSaveButton();
        void onClickAddDate();
        String getErr(String err);
        void addImageClicked();
    }
}
