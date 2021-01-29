package com.example.moviesapp.interfaces;

import com.example.moviesapp.models.EntityFilm;

public interface IForm {

    interface View {
        void finishFormActivity(EntityFilm entityFilm);

        void showDatePicker();

        void alertRemoveImage();

        void selectPicture();

        void selectImage();

        void removeImage();

        void IntentChooser();

        void showError();

    }

    interface Presenter {
        void onClickSaveButton(EntityFilm entityFilm);

        void onClickAddDate();

        String getErr(String err);

        void onClickDeleteForm();

        void onClickAcceptDelete(EntityFilm entityFilm);

        void onClickImage();

        void PermissionGranted();

        void PermissionDenied();

        String[] getGenres();
    }
}
