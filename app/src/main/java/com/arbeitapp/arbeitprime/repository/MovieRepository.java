package com.arbeitapp.arbeitprime.repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.room.Room;
import com.arbeitapp.arbeitprime.api.ApiInterface;
import com.arbeitapp.arbeitprime.api.RetrofitService;
import com.arbeitapp.arbeitprime.database.MoviesDao;
import com.arbeitapp.arbeitprime.database.MoviesDataBase;
import com.arbeitapp.arbeitprime.models.Movie;
import com.arbeitapp.arbeitprime.models.Respuesta;
import com.arbeitapp.arbeitprime.utils.context.MyApp;
import com.arbeitapp.arbeitprime.utils.others.Utils;
import com.arbeitapp.arbeitprime.utils.network.NetworkBoundResource;
import com.arbeitapp.arbeitprime.utils.network.Resource;
import java.util.List;
import retrofit2.Call;

public class MovieRepository {

    private final ApiInterface apiInterface;
    private final MoviesDao moviesDao;

    public MovieRepository(){

        //ROOM
        MoviesDataBase moviesDataBase = Room.databaseBuilder(
                MyApp.getMyContext(), MoviesDataBase.class,
                "db_movies").build();

        moviesDao = moviesDataBase.getMoviesDao();

        //API
        apiInterface = RetrofitService.createService(ApiInterface.class);

    }

    public LiveData<Resource<List<Movie>>> getTopMovies(){

        return new NetworkBoundResource<List<Movie>, Respuesta>(){

            @Override
            protected void saveCallResult(@NonNull Respuesta item) {
                moviesDao.saveMovies(item.getResult());
            }

            @NonNull
            @Override
            protected LiveData<List<Movie>> loadFromDb() {
                return moviesDao.loadMovies();
            }

            @NonNull
            @Override
            protected Call<Respuesta> createCall() {
                String lenguaje = Utils.getLanguage();
                int paginas = 1;
                String region = "";

                return apiInterface.getTop10Movies(lenguaje, paginas, region);
            }
        }.getAsLiveData();
    }
}
