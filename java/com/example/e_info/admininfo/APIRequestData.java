package com.example.e_info.admininfo;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIRequestData {
    @GET("readInfo.php")
    Call<ResponseModel> ardRetriveData();

    @FormUrlEncoded
    @POST("deleteInfo.php")
    Call<ResponseModel> ardDeleteData(
            @Field("id_info") int id_info
    );

}
