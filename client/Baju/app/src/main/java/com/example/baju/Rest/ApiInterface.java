package com.example.baju.Rest;

import com.example.baju.Model.GetSepatu;
import com.example.baju.Model.PostPutDelSepatu;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;

public interface ApiInterface {
    @GET("sepatu_android")
    Call<GetSepatu> getSepatu();
    @FormUrlEncoded
    @POST("sepatu")
    Call<PostPutDelSepatu> postSepatu(@Field("size") String size, @Field("color") String color);
    @FormUrlEncoded
    @PUT("sepatu")
    Call<PostPutDelSepatu> putSepatu(@Field("id_spt") String id_spt,
                                     @Field("size") String size,
                                     @Field("color") String color);
    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "sepatu", hasBody = true)
    Call<PostPutDelSepatu> deleteSepatu(@Field("id") String id);
}