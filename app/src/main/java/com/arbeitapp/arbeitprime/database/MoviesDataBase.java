package com.arbeitapp.arbeitprime.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import com.arbeitapp.arbeitprime.models.Movie;

@Database(entities = {Movie.class}, version = 1, exportSchema = false)
public abstract class MoviesDataBase extends RoomDatabase {

    @SuppressWarnings("WeakerAccess")
    public abstract MoviesDao getMoviesDao();

   // private static MoviesDao instance;

}
