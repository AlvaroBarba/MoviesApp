package com.example.moviesapp.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

import com.example.moviesapp.R;

public class AppCRUDActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_crud);
        Toolbar toolbar = findViewById(R.id.AppCrudToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Sobre Nosotros");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}