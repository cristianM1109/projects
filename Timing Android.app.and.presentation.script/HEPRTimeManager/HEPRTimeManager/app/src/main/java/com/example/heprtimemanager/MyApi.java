package com.example.heprtimemanager;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface MyApi {

    @FormUrlEncoded
    @POST("insertdata.php")
    Call<ResponseBody> insertdata(
            @Field("name")String name,
            @Field("TAG_ID")String TAG_ID,
            @Field("CAT")String CAT,
            @Field("number")String number
    );


    @GET("getdata.php")
    Call<List<Rider>> getAll(
    );

   //-------------------------------------SyncEvent-----------------------------------------------//

    @FormUrlEncoded
    @POST("insertevent.php")
    Call<ResponseBody> insertevents(
            @Field("TAG_ID")String TAG_ID,
            @Field("timestamp")String timestamp,
            @Field("CP")String CP,
            @Field("Day") String Day
    );


}
