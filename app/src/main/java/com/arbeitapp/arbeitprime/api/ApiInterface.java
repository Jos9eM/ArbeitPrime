package com.arbeitapp.arbeitprime.api;

import com.arbeitapp.arbeitprime.models.Detail;
import com.arbeitapp.arbeitprime.models.Respuesta;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("movie/top_rated")
    Call<Respuesta> getTop10Movies(
            @Query("language") String language,
            @Query("page") Integer page,
            @Query("region") String region);

    @GET("movie/{movie_id}")
    Call<Detail> getDetailMovie(
            @Path("movie_id") Integer id,
            @Query("language") String language,
            @Query("append_to_response") String append);

}
