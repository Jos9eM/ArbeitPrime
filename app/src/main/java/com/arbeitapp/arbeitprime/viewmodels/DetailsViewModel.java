package com.arbeitapp.arbeitprime.viewmodels;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.arbeitapp.arbeitprime.models.Detail;
import com.arbeitapp.arbeitprime.repository.DetailRepository;

public class DetailsViewModel extends ViewModel {

    private MutableLiveData<Detail> movie;
    private DetailRepository movieRepository;


    public void init(int id){
        if (movie != null){
            return;
        }

        movieRepository = DetailRepository.getInstance();
        movie = movieRepository.getMovieById(id);
    }

    public LiveData<Detail> getMovie(){
        return movie;
    }

}
