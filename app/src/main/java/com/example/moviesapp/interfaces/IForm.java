package com.example.moviesapp.interfaces;

public interface IForm {

    public interface View{
        void finishFormActivity();
        void showDatePicker();
    }

    public  interface  Presenter{
        void onClickSaveButton();
        void onClickAddDate();
        String getErr(String err);
    }
}
