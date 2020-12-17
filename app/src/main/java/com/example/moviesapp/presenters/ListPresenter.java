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

    @SuppressLint("LongLogTag")
    public void onClickSearchButton(){
        Log.d(TAG, "Inside OnClickSearchButton");
        view.startSearchActivity();
    }

    @SuppressLint("LongLogTag")
    public void onClickAPPCRUD(){
        Log.d(TAG, "Inside OnClickAppCRUD");
        view.startAppCRUDActivity();
    }

    @SuppressLint("LongLogTag")
    public void onClickRecyclerViewItem(String id) {
        Log.d(TAG, "Inside onClickRecyclerViewItem");
        view.startFormActivity(id);

    }

    @SuppressLint("LongLogTag")
    @Override
    public void onSwipe(int position) {
        Log.d(TAG, "Inside onSwipe");
        view.onItemSwipe(position);
    }

    @SuppressLint("LongLogTag")
    @Override
    public void showToast() {
        Log.d(TAG, "Inside showToast");
        view.showDeleteToast();
    }

}
