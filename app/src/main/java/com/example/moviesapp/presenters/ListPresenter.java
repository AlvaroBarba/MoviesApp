package com.example.moviesapp.presenters;

import android.annotation.SuppressLint;
import android.util.Log;

import com.example.moviesapp.interfaces.IList;

public class ListPresenter implements IList.Presenter {

    private IList.View view;
    private static final String TAG  = "presenters/ListPresenter";

    public ListPresenter(IList.View view) {
        this.view=view;
    }

    @SuppressLint("LongLogTag")
    @Override
    public void onClickAddMovie() {
        Log.d(TAG,"Inside OnClickAddMovie");
        view.startFormActivity();
    }

}
