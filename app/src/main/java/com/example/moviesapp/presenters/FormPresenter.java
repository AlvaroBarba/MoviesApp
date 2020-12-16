package com.example.moviesapp.presenters;

import android.annotation.SuppressLint;
import android.util.Log;

import com.example.moviesapp.R;
import com.example.moviesapp.interfaces.IForm;
import com.example.moviesapp.views.MyApplication;

public class FormPresenter implements IForm.Presenter{

    private static final String TAG = "presenters/FormPresenter";
    private IForm.View view;

    public FormPresenter(IForm.View view) {
        this.view=view;
    }

    @SuppressLint("LongLogTag")
    @Override
    public void onClickSaveButton() {
        Log.d(TAG,"Inside OnClickSaveButton");
        view.finishFormActivity();
    }

    @Override
    public void onClickAddDate(){
        view.showDatePicker();
    }


    @SuppressLint("LongLogTag")
    @Override
    public String getErr(String err) {
        Log.d(TAG,"Inside getErr");
        String err_msg = "";
        switch (err){
            case "title_1":
                err_msg = MyApplication.getContext().getResources().getString(R.string.fieldTitleEmpty);
                break;
            case "title_2":
                err_msg = MyApplication.getContext().getResources().getString(R.string.FieldTitleWrong);
                break;
            case "director_1":
                err_msg = MyApplication.getContext().getResources().getString(R.string.FieldDirectorEmpty);
                break;
            case "director_2":
                err_msg = MyApplication.getContext().getResources().getString(R.string.FieldDirectorWrong);
                break;
            case "synopsis_1":
                err_msg = MyApplication.getContext().getResources().getString(R.string.FieldSynopsisEmpty);
                break;
            case "synopsis_2":
                err_msg = MyApplication.getContext().getResources().getString(R.string.FieldSynopsisWrong);
                break;
            case "date_1":
                err_msg = MyApplication.getContext().getResources().getString(R.string.FieldDateEmpty);
                break;
            case "date_2":
                err_msg = MyApplication.getContext().getResources().getString(R.string.FieldDateWrong);
                break;
            case "rate_1":
                err_msg = MyApplication.getContext().getResources().getString(R.string.FieldRateEmpty);
                break;
            case "rate_2":
                err_msg = MyApplication.getContext().getResources().getString(R.string.FieldRateWrong);
                break;
            default:
                break;
        }
        return err_msg;
    }
}
