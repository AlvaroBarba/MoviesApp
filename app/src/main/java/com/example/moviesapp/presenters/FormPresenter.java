package com.example.moviesapp.presenters;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;

import androidx.core.content.ContextCompat;

import com.example.moviesapp.R;
import com.example.moviesapp.interfaces.IForm;
import com.example.moviesapp.models.EntityFilm;
import com.example.moviesapp.models.FilmModel;
import com.example.moviesapp.views.MyApplication;

import java.util.ArrayList;

public class FormPresenter implements IForm.Presenter {

    private static final String TAG = "presenters/FormPresenter";
    private final IForm.View view;
    private final FilmModel filmModel = new FilmModel();

    public FormPresenter(IForm.View view) {
        this.view = view;
    }

    @SuppressLint("LongLogTag")
    @Override
    public void onClickSaveButton(EntityFilm entityFilm, boolean flag) {
        Log.d(TAG, "Inside OnClickSaveButton");
        if (flag) {
            filmModel.insert(entityFilm);
        } else {
            filmModel.updateFilm(entityFilm);
        }
    }

    @Override
    public void onClickAddDate(){
        view.showDatePicker();
    }


    @SuppressLint("LongLogTag")
    @Override
    public String getErr(String err) {
        Log.d(TAG,"Inside getErr");
        String err_msg = "";
        switch (err){
            case "title_1":
                err_msg = MyApplication.getContext().getResources().getString(R.string.fieldTitleEmpty);
                break;
            case "title_2":
                err_msg = MyApplication.getContext().getResources().getString(R.string.FieldTitleWrong);
                break;
            case "director_1":
                err_msg = MyApplication.getContext().getResources().getString(R.string.FieldDirectorEmpty);
                break;
            case "director_2":
                err_msg = MyApplication.getContext().getResources().getString(R.string.FieldDirectorWrong);
                break;
            case "synopsis_1":
                err_msg = MyApplication.getContext().getResources().getString(R.string.FieldSynopsisEmpty);
                break;
            case "synopsis_2":
                err_msg = MyApplication.getContext().getResources().getString(R.string.FieldSynopsisWrong);
                break;
            case "date_1":
                err_msg = MyApplication.getContext().getResources().getString(R.string.FieldDateEmpty);
                break;
            case "date_2":
                err_msg = MyApplication.getContext().getResources().getString(R.string.FieldDateWrong);
                break;
            case "rate_1":
                err_msg = MyApplication.getContext().getResources().getString(R.string.FieldRateEmpty);
                break;
            case "rate_2":
                err_msg = MyApplication.getContext().getResources().getString(R.string.FieldRateWrong);
                break;
            default:
                break;
        }
        return err_msg;
    }

    public void onClickDeleteForm() {
        view.alertRemoveImage();
    }

    public void onClickAcceptDelete() {
        view.finishFormActivity();
    }

    public void onClickImage() {
        int WriteExternalStoragePermission = ContextCompat.checkSelfPermission(MyApplication.getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
        Log.d("FormPresenter", "WRITE_EXTERNAL_STORAGE Permission: " + WriteExternalStoragePermission);

        if (WriteExternalStoragePermission != PackageManager.PERMISSION_GRANTED) {
            //------PERMISO DENEGADO
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                view.IntentChooser();
            } else {
                view.showError();
            }
        } else {
            //--------PERMISO ACEPTADO-------
            view.selectImage();
        }
    }

    public void PermissionGranted() {
        view.selectImage();
    }

    public void PermissionDenied() {
        view.showError();
    }

    @Override
    public boolean insert(EntityFilm film) {
        return filmModel.insert(film);
    }

    @Override
    public ArrayList<String> getAllGenres() {
        return filmModel.getAllGenres();
    }

    @Override
    public EntityFilm getItemById(String id) {
        return filmModel.getById(id);
    }


}
