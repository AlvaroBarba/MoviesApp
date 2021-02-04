package com.example.moviesapp.views;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.moviesapp.R;
import com.example.moviesapp.interfaces.ISearch;
import com.example.moviesapp.presenters.SearchPresenter;

import java.util.ArrayList;
import java.util.Calendar;

public class SearchActivity extends AppCompatActivity implements ISearch.View {

    private static final String TAG = "/views/ListActivity";
    private ISearch.Presenter presenter;
    public EditText editTextDate;
    public ImageButton dateButton;
    public Spinner spinner;
    public EditText editTextTitle;
    public final Calendar c = Calendar.getInstance();
    public final int mes = c.get(Calendar.MONTH);
    public final int dia = c.get(Calendar.DAY_OF_MONTH);
    public final int anio = c.get(Calendar.YEAR);
    public ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        presenter = new SearchPresenter(this);
        Toolbar toolbar = findViewById(R.id.searchToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.parametersOfSearch);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        editTextDate = findViewById(R.id.dateSearch);
        editTextTitle = findViewById(R.id.titleForSearch);


        spinner = (Spinner) findViewById(R.id.searchSpinner);
        ArrayList<String> types = presenter.getAllGenres();
        types.add(0, getString(R.string.SelectSpinner));
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, types);
        spinner.setAdapter(adapter);

        ImageButton dateButton = findViewById(R.id.searchDateButton);
        ImageButton searchButton = findViewById(R.id.searchButton);
        dateButton.setOnClickListener(v -> {
            presenter.onClickDateButton();
        });
        searchButton.setOnClickListener(v -> {
            presenter.onClickSearchButton();
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
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
                    String diaFormateado = (dayOfMonth < 10) ? CERO + dayOfMonth : String.valueOf(dayOfMonth);
                    //Formateo el mes obtenido: antepone el 0 si son menores de 10
                    String mesFormateado = (mesActual < 10) ? CERO + mesActual : String.valueOf(mesActual);
                    //Muestro la fecha con el formato deseado
                    editTextDate.setText(diaFormateado + BARRA + mesFormateado + BARRA + year);


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
    public void finishSearchActivity() {
        Intent intent = new Intent();
        if (editTextDate != null && !editTextDate.getText().toString().isEmpty()) {
            intent.putExtra("date", editTextDate.getText().toString());
        }
        if (editTextTitle != null && !editTextTitle.getText().toString().isEmpty()) {
            intent.putExtra("title", editTextTitle.getText().toString());
        }
        if (spinner != null && !spinner.getSelectedItem().toString().equals(getString(R.string.SelectSpinner))) {
            intent.putExtra("genre", spinner.getSelectedItem().toString());
        }
        setResult(1, intent);
        finish();
    }
}