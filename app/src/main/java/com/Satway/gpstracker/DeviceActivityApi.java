package com.Satway.gpstracker;


import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface DeviceActivityApi {

    String BASE_URL="http://68.183.92.233:8000/api/dealerapp/";

    @FormUrlEncoded
    @POST("android_imei_dealer")
    Call<DeviceActivityPOJO> getdevicedata(@Field("imeino") String imeino);

}
