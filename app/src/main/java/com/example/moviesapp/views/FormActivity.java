package com.example.moviesapp.views;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;

import com.example.moviesapp.R;
import com.example.moviesapp.interfaces.IForm;
import com.example.moviesapp.models.EntityFilm;
import com.example.moviesapp.presenters.FormPresenter;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;

public class FormActivity extends AppCompatActivity implements IForm.View {

    private IForm.Presenter presenter;
    private static final String TAG = "/views/FormActivity";
    public ImageButton addDate;
    public ImageButton addImage;
    public Button deleteImage;
    public Button addGenre;
    private final EntityFilm fEntity = new EntityFilm();
    public final Calendar c = Calendar.getInstance();
    public final int mes = c.get(Calendar.MONTH);
    public final int dia = c.get(Calendar.DAY_OF_MONTH);
    public final int anio = c.get(Calendar.YEAR);
    public Switch seeFilm;
    private static final int REQUEST_FILM_IMAGE = 200;
    private static final int REQUEST_SELECT_IMAGE = 201;
    private Uri uri;
    final private int CODE_WRITE_EXTERNAL_STORAGE_PERMISSION = 123;
    private ConstraintLayout constraintLayoutFormActivity;
    public Spinner spinner;
    private String id = null;
    private ArrayAdapter<String> adapter;
    private Button delete;
    private TextInputLayout titleTIL, directorTIL, synopsisTIL, dateTIL, rateTIL;
    private TextInputEditText titleET, directorET, synopsisET, dateET, rateET;
    private boolean update = true;
    private String Photo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "Inside OnCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        constraintLayoutFormActivity = findViewById(R.id.FormConstraintLayout);
        presenter = new FormPresenter(this);


        Button button = findViewById(R.id.save);
        addGenre = findViewById(R.id.addOption);
        addDate = findViewById(R.id.addDate);
        addImage = findViewById(R.id.addImage);
        deleteImage = findViewById(R.id.deleteImage);
        seeFilm = findViewById(R.id.Watched);
        delete = findViewById(R.id.deleteForm);

        Toolbar toolbar = findViewById(R.id.FormToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.formTitle);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        spinner = (Spinner) findViewById(R.id.genreSpinner);

        ArrayList<String> types = presenter.getAllGenres();
        types.add(0, getString(R.string.SelectSpinner));
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, types);
        spinner.setAdapter(adapter);


        AlertDialog.Builder builder = new AlertDialog.Builder(FormActivity.this);
        builder.setTitle(R.string.newGenre);
        builder.setMessage(R.string.InsertNewGenre);
        EditText text = new EditText(FormActivity.this);
        text.setInputType(InputType.TYPE_CLASS_TEXT);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        text.setLayoutParams(lp);
        builder.setPositiveButton(R.string.Insert, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                adapter.add(text.getText().toString());
                spinner.setSelection(adapter.getPosition(text.getText().toString()));
                Toast.makeText(getApplicationContext(), R.string.addedSuccess, Toast.LENGTH_LONG).show();
                Log.i(TAG, "Yes button Clicked");
            }
        });
        builder.setNeutralButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.i(TAG, "Cancel button Clicked");
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.setView(text);
        addGenre.setOnClickListener(v -> {
            alertDialog.show();
        });


        EntityFilm entityFilm = new EntityFilm();
        titleTIL = findViewById(R.id.film);
        titleET = findViewById(R.id.filmET);
        directorTIL = findViewById(R.id.director);
        directorET = findViewById(R.id.directorET);
        synopsisTIL = findViewById(R.id.synopsis);
        synopsisET = findViewById(R.id.synopsisET);
        dateTIL = findViewById(R.id.calendar);
        dateET = findViewById(R.id.calendarET);
        rateTIL = findViewById(R.id.rateUs);
        rateET = findViewById(R.id.rateUsET);

        titleET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    Log.d(TAG, "Exit title Edit text");
                    if (entityFilm.setTitle(titleET.getText().toString()) == 1) {
                        titleTIL.setError(presenter.getErr("title_1"));
                        titleTIL.setErrorTextColor(ColorStateList.valueOf(Color.RED));
                    } else if (entityFilm.setTitle(titleET.getText().toString()) == 2) {
                        titleTIL.setError(presenter.getErr("title_2"));
                        titleTIL.setErrorTextColor(ColorStateList.valueOf(Color.RED));
                    } else {
                        Log.d(TAG, "Title input editText");
                        titleTIL.setError(null);

                    }
                }
            }
        });

        directorET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    Log.d(TAG, "Exit director editText");
                    if (entityFilm.setDirector(directorET.getText().toString()) == 1) {
                        directorTIL.setError(presenter.getErr("director_1"));
                        directorTIL.setErrorTextColor(ColorStateList.valueOf(Color.RED));
                    } else if (entityFilm.setDirector(directorET.getText().toString()) == 2) {
                        directorTIL.setError(presenter.getErr("director_2"));
                        directorTIL.setErrorTextColor(ColorStateList.valueOf(Color.RED));
                    } else {
                        Log.d(TAG, "Director input editText");
                        directorTIL.setError(null);
                    }
                }
            }
        });

        synopsisET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    Log.d(TAG, "Exit synopsis editText");
                    if (entityFilm.setSynopsis(synopsisET.getText().toString()) == 1) {
                        synopsisTIL.setError(presenter.getErr("synopsis_1"));
                        synopsisTIL.setErrorTextColor(ColorStateList.valueOf(Color.RED));
                    } else if (entityFilm.setSynopsis(synopsisET.getText().toString()) == 2) {
                        synopsisTIL.setError(presenter.getErr("synopsis_2"));
                        synopsisTIL.setErrorTextColor(ColorStateList.valueOf(Color.RED));
                    } else {
                        Log.d(TAG, "Title input editText");
                        synopsisTIL.setError(null);
                    }
                }
            }
        });

        dateET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    Log.d(TAG, "Exit date editText");
                    if (entityFilm.setDate(dateET.getText().toString()) == 1) {
                        dateTIL.setError(presenter.getErr("date_1"));
                        dateTIL.setErrorTextColor(ColorStateList.valueOf(Color.RED));
                    } else if (entityFilm.setDate(dateET.getText().toString()) == 2) {
                        dateTIL.setError(presenter.getErr("date_2"));
                        dateTIL.setErrorTextColor(ColorStateList.valueOf(Color.RED));
                    } else {
                        Log.d(TAG, "Date input editText");
                        dateTIL.setError(null);
                    }
                }
            }
        });

        rateET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    Log.d(TAG, "Exit rate editText");
                    if (entityFilm.setRate(rateET.getText().toString()) == 1) {
                        rateTIL.setError(presenter.getErr("rate_1"));
                        rateTIL.setErrorTextColor(ColorStateList.valueOf(Color.RED));
                    } else if (entityFilm.setRate(rateET.getText().toString()) == 2) {
                        rateTIL.setError(presenter.getErr("rate_2"));
                        rateTIL.setErrorTextColor(ColorStateList.valueOf(Color.RED));
                    } else {
                        Log.d(TAG, "rate input editText");
                        rateTIL.setError(null);
                    }
                }
            }
        });


        ImageButton galleryButton = (ImageButton) findViewById(R.id.addImage);
        galleryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onClickImage();
            }
        });

        removeImage();

        addDate.setOnClickListener(v -> {
            presenter.onClickAddDate();
        });


        id = getIntent().getStringExtra("id");

        if (id != null) {
            EntityFilm film = presenter.getItemById(id);
            fEntity.setId(id);

            titleET.setText(film.getTitle());
            synopsisET.setText(film.getSynopsis());
            dateET.setText(film.getDate());
            directorET.setText(film.getDirector());
            rateET.setText(film.getRate());
            seeFilm.setChecked(film.isWatched());
            Photo = film.getPhoto();
            byte[] decodedString = Base64.decode(Photo, Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            galleryButton.setImageBitmap(decodedByte);
            spinner.setSelection(adapter.getPosition(film.getGenre()));


        } else {
            Button delete2 = (Button) findViewById(R.id.deleteForm);
            delete2.setVisibility(View.INVISIBLE);
        }

        button.setOnClickListener(v -> {
            if (fEntity.setTitle(titleET.getText().toString()) == 0 &&
                    fEntity.setSynopsis(synopsisET.getText().toString()) == 0 &&
                    fEntity.setRate(rateET.getText().toString()) == 0 &&
                    fEntity.setDate(dateET.getText().toString()) == 0) {

                fEntity.setWatched(seeFilm.isChecked());


                if (galleryButton != null && galleryButton.getDrawable() != null) {
                    Bitmap bitmap = ((BitmapDrawable) galleryButton.getDrawable()).getBitmap();
                    if (bitmap != null) {
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                        byte[] byteArray = byteArrayOutputStream.toByteArray();
                        String fotoEnBase64 = Base64.encodeToString(byteArray, Base64.DEFAULT);
                        fEntity.setPhoto(fotoEnBase64);
                    }
                }

                fEntity.setGenre(spinner.getSelectedItem().toString());
                if (id != null) {
                    update = false;
                }
                presenter.onClickSaveButton(fEntity, update);

            } else {
                Log.d(TAG, "Error during saving data...");
            }
            finish();
        });

    }


    public void alertRemoveImage() {
        AlertDialog.Builder builder = new AlertDialog.Builder(FormActivity.this);
        builder.setTitle(R.string.removeAlert);

        builder.setPositiveButton(R.string.confirmDelete, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                presenter.onClickAcceptDelete();
                // Toast.makeText(getApplicationContext(),"Yes button Clicked", Toast.LENGTH_LONG).show();
                Log.i(TAG, "Yes button Clicked!");
            }
        });

        builder.setNeutralButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //    Toast.makeText(getApplicationContext(),"Cancel button Clicked",Toast.LENGTH_LONG).show();
                Log.i(TAG,"Cancel button Clicked!");
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void selectPicture(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(
                Intent.createChooser(intent, getString(R.string.selectImage)),
                REQUEST_SELECT_IMAGE);
    }

    public void selectImage() {
        // Se le pide al sistema una imagen del dispositivo
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(
                Intent.createChooser(intent, getResources().getString(R.string.selectImage)),
                REQUEST_SELECT_IMAGE);
    }

    public void removeImage() {
        deleteImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageButton buttonGallery = findViewById(R.id.addImage);
                buttonGallery.setImageBitmap(null);
            }
        });
    }

        public void IntentChooser(){
            ActivityCompat.requestPermissions(FormActivity.this, new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, CODE_WRITE_EXTERNAL_STORAGE_PERMISSION);
        }

        public void showError(){
            Snackbar.make(findViewById(android.R.id.content), getResources().getString(R.string.writeExternalStorageDENIED), Snackbar.LENGTH_LONG).show();
        }

        public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
            switch (requestCode) {
                case CODE_WRITE_EXTERNAL_STORAGE_PERMISSION:
                    if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        presenter.PermissionGranted();
                    } else {
                        presenter.PermissionDenied();
                    }
                    break;
                default:
                    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            }
        }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {

            case (REQUEST_FILM_IMAGE):
                if (resultCode == Activity.RESULT_OK) {
                    // Se carga la imagen desde un objeto URI al imageView
                    ImageButton imageButton = findViewById(R.id.addImage);
                    imageButton.setImageURI(uri);

                    // Se le envía un broadcast a la Galería para que se actualice
                    Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                    mediaScanIntent.setData(uri);
                    sendBroadcast(mediaScanIntent);

                } else if (resultCode == Activity.RESULT_CANCELED) {
                    // Se borra el archivo temporal
                    File file = new File(uri.getPath());
                    file.delete();
                }
                break;

            case (REQUEST_SELECT_IMAGE):
                if (resultCode == Activity.RESULT_OK) {
                    // Se carga la imagen desde un objeto Bitmap
                    Uri selectedImage = data.getData();
                    String selectedPath = selectedImage.getPath();

                    if (selectedPath != null) {
                        // Se leen los bytes de la imagen
                        InputStream imageStream = null;
                        try {
                            imageStream = getContentResolver().openInputStream(selectedImage);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }

                        // Se transformam los bytes de la imagen a un Bitmap
                        Bitmap bmp = BitmapFactory.decodeStream(imageStream);

                        // Se carga el Bitmap en el ImageView
                        Bitmap imageScaled = Bitmap.createScaledBitmap(bmp, 200, 200, false);
                        ImageButton imageButton = findViewById(R.id.addImage);
                        imageButton.setImageBitmap(imageScaled);
                    }
                }
                break;
        }
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
    public void finishFormActivity(EntityFilm entityFilm) {
        Log.d(TAG, "Inside startFormActivity(Entity)");
        presenter.insert(entityFilm);
        finish();
    }

    @Override
    public void finishFormActivity() {
        Log.d(TAG, "Inside startFormActivity()");
        finish();
    }


}