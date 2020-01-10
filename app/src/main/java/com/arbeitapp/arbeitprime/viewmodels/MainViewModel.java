package com.arbeitapp.arbeitprime.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.arbeitapp.arbeitprime.models.Movie;
import com.arbeitapp.arbeitprime.repository.MovieRepository;
import com.arbeitapp.arbeitprime.utils.network.Resource;
import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends ViewModel {
    // TODO: Implement the ViewModel

    private LiveData<Resource<List<Movie>>> topMovies;
    private MovieRepository movieRepository;

    public MainViewModel(){
        movieRepository = new MovieRepository();
        topMovies = movieRepository.getTopMovies();
    }

    public LiveData<Resource<List<Movie>>> getTop10Movies(){
        return topMovies;
    }
}
