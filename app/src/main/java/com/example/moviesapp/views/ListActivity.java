package com.example.moviesapp.views;

import android.content.Intent;
import android.os.Bundle;

import com.example.moviesapp.R;
import com.example.moviesapp.interfaces.IList;
import com.example.moviesapp.presenters.ListPresenter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;

public class ListActivity extends AppCompatActivity implements IList.View {

    private static  final String TAG = "/views/ListActivity";
    private IList.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        presenter = new ListPresenter(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Listado de Películas");

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(v -> {
            presenter.onClickAddMovie();
        });
    }

    @Override
    protected void onRestart() {
        Log.d(TAG, "Inside onRestart()");
        super.onRestart();
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "Inside onStop()");
        super.onStop();
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "Inside onResume()");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "Inside onPause()");
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "Inside onDestroy()");
        super.onDestroy();
    }

    @Override
    protected void onStart() {
        Log.d(TAG, "Inside onStart()");
        super.onStart();
    }

    @Override
    public void startFormActivity() {
        Log.d(TAG, "Inside startFormActivity()");
        Intent intent = new Intent(getApplicationContext(), FormActivity.class);
        startActivity(intent);
    }
}