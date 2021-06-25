package com.example.e_info.info;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIRequestData {
    @GET("readInfo.php")
    Call<ResponseModel> ardRetriveData();

    @FormUrlEncoded
    @POST("createInfo.php")
    Call<ResponseModel> ardCreateData(
            @Field("mk") String mk,
            @Field("judul") String judul,
            @Field("deskripsi") String deskripsi,
            @Field("masuk") String masuk
    );

}
