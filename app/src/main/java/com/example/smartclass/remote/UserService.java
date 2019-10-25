package com.example.smartclass.remote;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserService {

    @FormUrlEncoded
    @POST("api/login")
    Call<ResponseBody>login(
            @Field("email")String email,
            @Field("password")String password
    );
}
