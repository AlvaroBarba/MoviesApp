package com.example.moviesapp.interfaces;

import com.example.moviesapp.models.EntityFilm;

import java.util.ArrayList;

public interface IList {

    interface View {
        void startFormActivity();

        void startFormActivity(String id);

        void startSearchActivity();

        void startAppCRUDActivity();

        void onItemSwipe(int position);

        void showDeleteToast();
    }

    interface Presenter {
        void onClickAddMovie();

        void onClickSearchButton();

        void onClickAPPCRUD();

        void onClickRecyclerViewItem(String id);

        void onSwipe(int position);

        void showToast();

        ArrayList<EntityFilm> getAllItemsSummarize();

        boolean insertOnBBDD(EntityFilm entityFilm);

        boolean deleteFilm(EntityFilm film);

        ArrayList<EntityFilm> getItemsByCriterion(String type, String content);

        ArrayList<EntityFilm> getItemsByAllCriterions(String genre, String date, String title);
    }



}
