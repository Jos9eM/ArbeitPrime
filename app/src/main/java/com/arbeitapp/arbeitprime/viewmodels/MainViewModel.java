package com.arbeitapp.arbeitprime.viewmodels;

import android.content.Context;
import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.room.Room;
import com.arbeitapp.arbeitprime.api.ApiInterface;
import com.arbeitapp.arbeitprime.database.MoviesDataBase;
import com.arbeitapp.arbeitprime.models.Respuesta;
import com.arbeitapp.arbeitprime.models.Movie;
import com.arbeitapp.arbeitprime.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends ViewModel {
    // TODO: Implement the ViewModel

    private final String API_KEY = "d51f8d7501c1891eb2eed1a790ea2a95";
    private MutableLiveData<List<Movie>> data;
    private List<Movie> movies;
    Context context;
    private MoviesDataBase moviesDataBase;

    public MainViewModel() {
    }

    public LiveData<List<Movie>> getMovies(){

        if (data == null){
            data = new MutableLiveData<>();
            movies = new ArrayList<>();     }

        return data;
    }

    public void loadJson(){

        final ApiInterface moviesInterface = MovieClient.getApi().create(ApiInterface.class);
        Call<Respuesta> call;

        String lenguaje = Utils.getLanguage();
        int paginas = 1;
        String region = "";

        call = moviesInterface.getTop10Movies(API_KEY, lenguaje, paginas, region);
        call.enqueue(new Callback<Respuesta>() {
            @Override
            public void onResponse(Call<Respuesta> call, Response<Respuesta> response) {
                if (response.isSuccessful() && response.body().getResult() != null) {

                    movies = response.body().getResult();
                    data.setValue(movies);
                    moviesDataBase = Room.databaseBuilder(,
                            MoviesDataBase.class, "moviesdb");

                } else {
                    Log.d("Retrofit error", String.valueOf(response.code()));
                    throw new RuntimeException(String.valueOf(response.code()));
                   /* String errorMessage;
                    switch (response.code()){
                        case 400:
                            errorMessage = "400 bad request";
                            break;
                        case 401:
                            errorMessage = "401 unauthorized request";
                            break;
                        case 404:
                            errorMessage = "404 not found";
                            break;
                        case 500:
                            errorMessage = "500 server broken";
                            break;
                        case 503:
                            errorMessage = "503 service unavailable";
                            break;
                        case 504:
                            errorMessage = "504 gateway timeout";
                            break;
                        default:
                            errorMessage = "Unknow error";
                            break;
                    }
                    alertNoResults(errorMessage);*/
                }
            }

            @Override
            public void onFailure(Call<Respuesta> call, Throwable t) {
                Log.d("Retrofit error", String.valueOf(t.getMessage()));
                throw new RuntimeException(t);
            }
        });
    }
}
