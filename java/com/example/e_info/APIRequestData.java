package com.example.e_info;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIRequestData {
    @GET("readTugas.php")
    Call<ResponseModel> ardRetriveData();

    @FormUrlEncoded
    @POST("createTugas.php")
    Call<ResponseModel> ardCreateData(
      @Field("mk") String mk,
      @Field("judul") String judul,
      @Field("deskripsi") String deskripsi,
      @Field("masuk") String masuk,
      @Field("deadline") String deadline
    );
}
