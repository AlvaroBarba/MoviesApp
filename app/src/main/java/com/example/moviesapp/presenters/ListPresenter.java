package com.example.moviesapp.presenters;

import android.util.Log;

import com.example.moviesapp.interfaces.IList;

public class ListPresenter implements IList.Presenter {

    private IList.View view;

    public ListPresenter(IList.View view) {
        this.view=view;
    }

    @Override
    public void onClickAddMovie() {
        //Log.d("presenters/ListPresenter","")
        view.startFormActivity();
    }

}
