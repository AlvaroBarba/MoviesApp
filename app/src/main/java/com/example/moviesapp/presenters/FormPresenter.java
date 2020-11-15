package com.example.moviesapp.presenters;

import android.annotation.SuppressLint;
import android.util.Log;

import com.example.moviesapp.interfaces.IForm;

public class FormPresenter implements IForm.Presenter{

    private static final String TAG = "presenters/FormPresenter";
    private IForm.View view;

    public FormPresenter(IForm.View view) {
        this.view=view;
    }

    @SuppressLint("LongLogTag")
    @Override
    public void onClickSaveButton() {
        Log.d(TAG,"Inside OnClickSaveButton");
        view.finishFormActivity();
    }
}
