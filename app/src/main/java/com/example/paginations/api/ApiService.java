package com.example.paginations.api;

import com.example.paginations.model.ImageModel;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;

import static com.example.paginations.api.ApiUtilities.API_KEY;

public interface ApiService {

    @Headers("Authorization: Client-ID"+API_KEY)
    @GET("/photos/")
    Call<List<ImageModel>> getImages(
            @Query("page") int page,
            @Query("per_page") int perPage
    );

}
