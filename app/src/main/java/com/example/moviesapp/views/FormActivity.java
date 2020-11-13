package com.example.moviesapp.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.example.moviesapp.R;
import com.example.moviesapp.interfaces.IForm;
import com.example.moviesapp.presenters.FormPresenter;

public class FormActivity extends AppCompatActivity implements IForm.View {

    private IForm.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        presenter = new FormPresenter(this);
        Button button = findViewById(R.id.save);
        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Formulario");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        button.setOnClickListener(v -> {
            presenter.onClickSaveButton();
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
    public void finishFormActivity() {
        Log.d("views/FormActivity", "Dentro de startFormActivity()");
        finish();
    }
}