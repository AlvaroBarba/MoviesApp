package com.example.moviesapp.views;

import android.content.Intent;
import android.os.Bundle;

import com.example.moviesapp.R;
import com.example.moviesapp.interfaces.IList;
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
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "Dentro de onclick en onCreate");
                presenter.onClickAddMovie();
            }
        });
    }

    @Override
    protected  void onResume(){
        super.onResume();
    }

    @Override
    protected  void onStart(){
        super.onStart();
    }

    @Override
    public void startFormActivity() {
        Log.d(TAG, "Dentro de startFormActivity()");
        Intent intent = new Intent(getApplicationContext(), FormActivity.class); //se quita el error al crear el formulario
        startActivity(intent);
    }
}