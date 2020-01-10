package com.arbeitapp.arbeitprime.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.arbeitapp.arbeitprime.api.ApiInterface;
import com.arbeitapp.arbeitprime.api.RequestInterceptor;
import com.arbeitapp.arbeitprime.api.RetrofitService;
import com.arbeitapp.arbeitprime.models.Detail;
import com.arbeitapp.arbeitprime.utils.others.Utils;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailRepository {

    private static DetailRepository detailRepository;
    private ApiInterface apiInterface;

    public static DetailRepository getInstance(){
        if (detailRepository == null){
            detailRepository = new DetailRepository();
        }
        return detailRepository;
    }

    public DetailRepository(){
        apiInterface = RetrofitService.createService(ApiInterface.class);
    }

    public MutableLiveData<Detail> getMovieById(int id){

        String lenguaje = Utils.getLanguage();
        Call<Detail> call;
        call = apiInterface.getDetailMovie(id, lenguaje, "" );
        MutableLiveData<Detail> movie = new MutableLiveData<>();

        call.enqueue(new Callback<Detail>() {
            @Override
            public void onResponse(Call<Detail> call, Response<Detail> response) {
                if (response.isSuccessful() && response.body() != null){
                    movie.setValue(response.body());
                } else {

                }
            }

            @Override
            public void onFailure(Call<Detail> call, Throwable t) {
                movie.setValue(null);
                Log.w("TAG", "requestFailed", t);
            }
        });
        return movie;
    }

}
