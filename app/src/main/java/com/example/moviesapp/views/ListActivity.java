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
        super.onRestart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void startFormActivity() {
        Log.d(TAG, "Dentro de startFormActivity()");
        Intent intent = new Intent(getApplicationContext(), FormActivity.class);
        startActivity(intent);
    }
}