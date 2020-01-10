package com.arbeitapp.arbeitprime.api;

import com.arbeitapp.arbeitprime.utils.others.Utils;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {

    private  static OkHttpClient.Builder okBuilder = new OkHttpClient.Builder()
            .addInterceptor(new RequestInterceptor());

    private  static OkHttpClient client = okBuilder.build();

    private  static Retrofit retrofit =  new Retrofit.Builder()
                .baseUrl(Utils.URL_BASE)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    public  static <S> S createService(Class<S> serviceClass) {
        return retrofit.create(serviceClass);
    }

}
