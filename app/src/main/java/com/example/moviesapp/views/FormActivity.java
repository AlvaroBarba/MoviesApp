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
    private static final String TAG = "/views/FormActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        presenter = new FormPresenter(this);
        Button button = findViewById(R.id.save);
        Toolbar toolbar = findViewById(R.id.FormToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.formTitle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        button.setOnClickListener(v -> {
            presenter.onClickSaveButton();
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
    public void finishFormActivity() {
        Log.d(TAG, "Inside startFormActivity()");
        finish();
    }


}