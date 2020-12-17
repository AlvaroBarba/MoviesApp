package com.example.moviesapp.interfaces;

public interface IList {

    public interface View{
        void startFormActivity();
        void startFormActivity(String id);
        void startSearchActivity();
        void startAppCRUDActivity();
        void onItemSwipe(int position);
        void showDeleteToast();
    }

    public  interface  Presenter{
        void onClickAddMovie();
        void onClickSearchButton();
        void onClickAPPCRUD();
        void onClickRecyclerViewItem(String id);
        void onSwipe(int position);
        void showToast();
    }



}
