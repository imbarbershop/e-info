package com.example.e_info.admintugas;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIRequestData {
    @GET("readTugas.php")
    Call<ResponseModel> ardRetriveData();

    @FormUrlEncoded
    @POST("deleteTugas.php")
    Call<ResponseModel> ardDeleteData(
            @Field("id_tugas") int id_tugas
    );
    
}
