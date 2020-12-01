package com.example.moviesapp.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.example.moviesapp.R;
import com.example.moviesapp.interfaces.ISearch;
import com.example.moviesapp.presenters.SearchPresenter;

import java.util.Calendar;

public class SearchActivity extends AppCompatActivity implements ISearch.View{

    private static  final String TAG = "/views/ListActivity";
    private ISearch.Presenter presenter;
    public EditText editTextDate;
    public ImageButton dateButton;
    public final Calendar c = Calendar.getInstance();
    public final int mes = c.get(Calendar.MONTH);
    public final int dia = c.get(Calendar.DAY_OF_MONTH);
    public final int anio = c.get(Calendar.YEAR);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        presenter = new SearchPresenter(this);
        Toolbar toolbar = findViewById(R.id.searchToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.parametersOfSearch);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Spinner spinner = (Spinner) findViewById(R.id.searchSpinner);
        String[] types = {
                getString(R.string.selectGenre),getString(R.string.TypeFiction), getString(R.string.TypeAction), getString(R.string.TypeDrama),
                getString(R.string.TypeComedy), getString(R.string.TypePolice), getString(R.string.TypeRomantic),
                getString(R.string.TypeChild)
        };
        spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, types));
        editTextDate = findViewById(R.id.dateSearch);
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
                    String diaFormateado = (dayOfMonth < 10)? CERO + String.valueOf(dayOfMonth):String.valueOf(dayOfMonth);
                    //Formateo el mes obtenido: antepone el 0 si son menores de 10
                    String mesFormateado = (mesActual < 10)? CERO + String.valueOf(mesActual):String.valueOf(mesActual);
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
        finish();
    }
}