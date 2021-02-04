package com.example.moviesapp.interfaces;

import com.example.moviesapp.models.EntityFilm;

import java.util.ArrayList;

public interface IForm {

    interface View {
        void finishFormActivity(EntityFilm entityFilm);

        void finishFormActivity();

        void showDatePicker();

        void alertRemoveImage();

        void selectPicture();

        void selectImage();

        void removeImage();

        void IntentChooser();

        void showError();

    }

    interface Presenter {
        void onClickSaveButton(EntityFilm entityFilm, boolean flag);

        void onClickAddDate();

        String getErr(String err);

        void onClickDeleteForm();

        void onClickAcceptDelete();

        void onClickImage();

        void PermissionGranted();

        void PermissionDenied();

        boolean insert(EntityFilm film);

        ArrayList<String> getAllGenres();

        EntityFilm getItemById(String id);
    }
}
