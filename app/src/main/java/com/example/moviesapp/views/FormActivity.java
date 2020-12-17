package com.example.moviesapp.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.moviesapp.R;
import com.example.moviesapp.interfaces.IForm;
import com.example.moviesapp.models.EntityFilm;
import com.example.moviesapp.presenters.FormPresenter;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;

public class FormActivity extends AppCompatActivity implements IForm.View {

    private IForm.Presenter presenter;
    private static final String TAG = "/views/FormActivity";
    public ImageButton addDate;
    public Button addGenre;
    public TextInputEditText dateET;
    public final Calendar c = Calendar.getInstance();
    public final int mes = c.get(Calendar.MONTH);
    public final int dia = c.get(Calendar.DAY_OF_MONTH);
    public final int anio = c.get(Calendar.YEAR);
    private String id;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "Inside OnCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        presenter = new FormPresenter(this);
        Button button = findViewById(R.id.save);
        addGenre = findViewById(R.id.addOption);
        addDate = findViewById(R.id.addDate);
        Toolbar toolbar = findViewById(R.id.FormToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.formTitle);
        Spinner spinner = (Spinner) findViewById(R.id.genreSpinner);
        String[] types = {
                getString(R.string.selectGenre),getString(R.string.TypeFiction), getString(R.string.TypeAction), getString(R.string.TypeDrama),
                getString(R.string.TypeComedy), getString(R.string.TypePolice), getString(R.string.TypeRomantic),
                getString(R.string.TypeChild), getString(R.string.TypeTerror)
        };
        spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, types));

        AlertDialog.Builder builder = new AlertDialog.Builder(FormActivity.this);
        builder.setTitle(R.string.newGenre);
        builder.setMessage(R.string.InsertNewGenre);
        EditText dialog = new EditText(FormActivity.this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        dialog.setLayoutParams(lp);
        builder.setPositiveButton(R.string.Insert, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), R.string.addedSuccess,Toast.LENGTH_LONG).show();
                Log.i(TAG, "Yes button Clicked");
            }
        });
        builder.setNeutralButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.i(TAG,"Cancel button Clicked");
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.setView(dialog);
        addGenre.setOnClickListener(v -> {
            alertDialog.show();
        });


        EntityFilm entityFilm = new EntityFilm();
        TextInputLayout titleTIL = findViewById(R.id.film);
        TextInputEditText titleET = findViewById(R.id.filmET);
        TextInputLayout directorTIL = findViewById(R.id.director);
        TextInputEditText directorET = findViewById(R.id.directorET);
        TextInputLayout synopsisTIL = findViewById(R.id.synopsis);
        TextInputEditText synopsisET = findViewById(R.id.synopsisET);
        TextInputLayout dateTIL = findViewById(R.id.calendar);
        dateET = findViewById(R.id.calendarET);
        TextInputLayout rateTIL = findViewById(R.id.rateUs);
        TextInputEditText rateET = findViewById(R.id.rateUsET);

        titleET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    Log.d(TAG, "Exit title Edit text");
                    if( entityFilm.setTitle(titleET.getText().toString()) == 1){
                        titleTIL.setError(presenter.getErr("title_1"));
                        titleTIL.setErrorTextColor(ColorStateList.valueOf(Color.RED));
                    }else if(entityFilm.setTitle(titleET.getText().toString()) == 2){
                        titleTIL.setError(presenter.getErr("title_2"));
                        titleTIL.setErrorTextColor(ColorStateList.valueOf(Color.RED));
                    }else{
                        Log.d(TAG, "Title input editText");
                        titleTIL.setError(null);

                    }
                }
            }
        });

        directorET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    Log.d(TAG, "Exit director editText");
                    if( entityFilm.setDirector(directorET.getText().toString()) == 1){
                        directorTIL.setError(presenter.getErr("director_1"));
                        directorTIL.setErrorTextColor(ColorStateList.valueOf(Color.RED));
                    }else if(entityFilm.setDirector(directorET.getText().toString()) == 2){
                        directorTIL.setError(presenter.getErr("director_2"));
                        directorTIL.setErrorTextColor(ColorStateList.valueOf(Color.RED));
                    }else{
                        Log.d(TAG, "Director input editText");
                        directorTIL.setError(null);
                    }
                }
            }
        });

        synopsisET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    Log.d(TAG, "Exit synopsis editText");
                    if( entityFilm.setSynopsis(synopsisET.getText().toString()) == 1){
                        synopsisTIL.setError(presenter.getErr("synopsis_1"));
                        synopsisTIL.setErrorTextColor(ColorStateList.valueOf(Color.RED));
                    }else if(entityFilm.setSynopsis(synopsisET.getText().toString()) == 2){
                        synopsisTIL.setError(presenter.getErr("synopsis_2"));
                        synopsisTIL.setErrorTextColor(ColorStateList.valueOf(Color.RED));
                    }else{
                        Log.d(TAG, "Title input editText");
                        synopsisTIL.setError(null);
                    }
                }
            }
        });

        dateET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    Log.d(TAG, "Exit date editText");
                    if( entityFilm.setDate(dateET.getText().toString()) == 1){
                        dateTIL.setError(presenter.getErr("date_1"));
                        dateTIL.setErrorTextColor(ColorStateList.valueOf(Color.RED));
                    }else if(entityFilm.setDate(dateET.getText().toString()) == 2){
                        dateTIL.setError(presenter.getErr("date_2"));
                        dateTIL.setErrorTextColor(ColorStateList.valueOf(Color.RED));
                    }else{
                        Log.d(TAG, "Date input editText");
                        dateTIL.setError(null);
                    }
                }
            }
        });

        rateET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    Log.d(TAG, "Exit rate editText");
                    if( entityFilm.setRate(rateET.getText().toString()) == 1){
                        rateTIL.setError(presenter.getErr("rate_1"));
                        rateTIL.setErrorTextColor(ColorStateList.valueOf(Color.RED));
                    }else if(entityFilm.setRate(rateET.getText().toString()) == 2){
                        rateTIL.setError(presenter.getErr("rate_2"));
                        rateTIL.setErrorTextColor(ColorStateList.valueOf(Color.RED));
                    }else{
                        Log.d(TAG, "rate input editText");
                        rateTIL.setError(null);
                    }
                }
            }
        });

        id = getIntent().getStringExtra("id");
        if(id!=null){
            titleET.setText(id);
        }else{

        }

        addDate.setOnClickListener(v -> {
            presenter.onClickAddDate();
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        button.setOnClickListener(v -> {
            presenter.onClickSaveButton();
        });
    }

    @Override
    public void showDatePicker() {
        String CERO = "0";
        String BARRA = "/";
        DatePickerDialog obtainDate = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                //Esta variable lo que realiza es aumentar en uno el mes ya que comienza desde 0 = enero
                final int mesActual = month + 1;
                //Formateo el día obtenido: antepone el 0 si son menores de 10
                String diaFormateado = (dayOfMonth < 10)? CERO + String.valueOf(dayOfMonth):String.valueOf(dayOfMonth);
                //Formateo el mes obtenido: antepone el 0 si son menores de 10
                String mesFormateado = (mesActual < 10)? CERO + String.valueOf(mesActual):String.valueOf(mesActual);
                //Muestro la fecha con el formato deseado
                dateET.setText(diaFormateado + BARRA + mesFormateado + BARRA + year);


            }
            //Estos valores deben ir en ese orden, de lo contrario no mostrara la fecha actual
            /**
             *También puede cargar los valores que usted desee
             */
        },anio, mes, dia);
        //Muestro el widget
        obtainDate.show();

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