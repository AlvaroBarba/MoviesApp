package com.example.moviesapp.interfaces;

public interface IForm {

    public interface View{
        void finishFormActivity();
        void showDatePicker();
        void alertRemoveImage();
        void selectPicture();
        void selectImage();
        void removeImage();
        void IntentChooser();
        void showError();

    }

    public  interface  Presenter{
        void onClickSaveButton();
        void onClickAddDate();
        String getErr(String err);
        void onClickDeleteForm();
        void onClickAcceptDelete();
        void onClickImage();
        void PermissionGranted();
        void PermissionDenied();
    }
}
