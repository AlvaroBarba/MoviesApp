package com.example.moviesapp.views;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviesapp.R;
import com.example.moviesapp.models.EntityFilm;

import java.util.ArrayList;

public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.FilmViewHolder> implements View.OnClickListener{

    private ArrayList<EntityFilm> items;
    private View.OnClickListener listener;

    // Clase interna:
    // Se implementa el ViewHolder que se encargará
    // de almacenar la vista del elemento y sus datos
    public static class FilmViewHolder
            extends RecyclerView.ViewHolder {


        private ImageView photo;
        private TextView title;
        private TextView synopsis;

        public FilmViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.firstName);
            synopsis = (TextView) itemView.findViewById(R.id.date);
            photo=(ImageView) itemView.findViewById(R.id.photo);
        }

        public void FilmBind(EntityFilm item) {
            title.setText(item.getTitle());
            synopsis.setText(item.getSynopsis());
            byte[] decodedString = Base64.decode(item.getPhoto(), Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            photo.setImageBitmap(decodedByte);
        }
    }

    // Contruye el objeto adaptador recibiendo la lista de datos
    public FilmAdapter(@NonNull ArrayList<EntityFilm> items) {
        this.items = items;
    }

    // Se encarga de crear los nuevos objetos ViewHolder necesarios
    // para los elementos de la colección.
    // Infla la vista del layout, crea y devuelve el objeto ViewHolder
    @Override
    public FilmViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_row, parent, false);
        row.setOnClickListener(this);

        FilmViewHolder filmViewHolder = new FilmViewHolder(row);
        return filmViewHolder;
    }

    // Se encarga de actualizar los datos de un ViewHolder ya existente.
    @Override
    public void onBindViewHolder(FilmViewHolder viewHolder, int position) {
        EntityFilm item = items.get(position);
        viewHolder.FilmBind(item);
    }

    // Indica el número de elementos de la colección de datos.
    @Override
    public int getItemCount() {
        return items.size();
    }

    // Asigna un listener al elemento
    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if(listener != null)
            listener.onClick(view);
    }


}