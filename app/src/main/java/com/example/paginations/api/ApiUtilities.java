package com.example.paginations.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiUtilities {

    public static final String BASE_URL = "https://api.unsplash.com";
    public static final String API_KEY = "-p7BgTkFB0GoeiosUSi5AZeH7XbogFrpi_c1wMfzpDg";

    public static Retrofit retrofit = null;

    public static ApiService getApiService(){
        if (retrofit==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(ApiService.class);
    }

}
