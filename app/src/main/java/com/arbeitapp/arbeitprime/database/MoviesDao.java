package com.arbeitapp.arbeitprime.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.arbeitapp.arbeitprime.models.Movie;

import java.util.List;

@Dao
public interface MoviesDao {

    @Query("SELECT * FROM movies LIMIT 10")
    LiveData<List<Movie>> loadMovies();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveMovies(List<Movie> movieList);

    @Query("DELETE FROM movies WHERE " + "id" + " = :ide")
    int deleteById(long ide);

    @Update
    int updateEntidad(Movie obj);

    @Insert
    long insert(Movie movie);

}
