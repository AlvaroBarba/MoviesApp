package com.example.moviesapp.presenters;

import android.annotation.SuppressLint;
import android.util.Log;

import com.example.moviesapp.interfaces.IForm;

public class FormPresenter implements IForm.Presenter{
    private IForm.View view;

    public FormPresenter(IForm.View view) {
        this.view=view;
    }

    @SuppressLint("LongLogTag")
    @Override
    public void onClickSaveButton() {
        Log.d("presenters/FormPresenter","Estamos en OnClickSaveButton");
        view.finishFormActivity();
    }
}
