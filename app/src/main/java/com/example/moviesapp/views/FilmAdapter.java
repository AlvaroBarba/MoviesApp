package com.example.moviesapp.views;

import android.content.Context;
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

public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.FilmViewHolder> implements View.OnClickListener {

    private static Context context;
    private View.OnClickListener listener;
    private final ArrayList<EntityFilm> items;

    // Contruye el objeto adaptador recibiendo la lista de datos
    public FilmAdapter(@NonNull ArrayList<EntityFilm> items, Context context) {
        this.items = items;
        FilmAdapter.context = context;
    }

    // Clase interna:
    // Se implementa el ViewHolder que se encargará
    // de almacenar la vista del elemento y sus datos
    public static class FilmViewHolder
            extends RecyclerView.ViewHolder {


        private final ImageView photo;
        private final TextView title;
        private final TextView date;

        public FilmViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.firstName);
            date = (TextView) itemView.findViewById(R.id.date);
            photo = (ImageView) itemView.findViewById(R.id.photo);
        }

        public void FilmBind(EntityFilm item) {
            title.setText(item.getTitle());
            date.setText(item.getDate());
            if(item.getPhoto() == null){
                photo.setBackground(MyApplication.getContext().getDrawable(R.drawable.ic_launcher_palomitas_foreground));
            }else{
                byte[] decodedString = Base64.decode(item.getPhoto(), Base64.DEFAULT);
                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                photo.setImageBitmap(decodedByte);
            }
        }
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