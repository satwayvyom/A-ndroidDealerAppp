package com.Satway.gpstracker;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitDeviceActivity {


    private static RetrofitDeviceActivity instance=null;

    private DeviceActivityApi myApi;

    private RetrofitDeviceActivity(){

        Retrofit retrofit =new Retrofit.Builder().baseUrl(DeviceActivityApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        myApi=retrofit.create(DeviceActivityApi.class);

    }


    public static synchronized RetrofitDeviceActivity getInstance()
    {
        if (instance==null)
        {
            instance=new RetrofitDeviceActivity();
        }
        return instance;
    }

    public DeviceActivityApi getMyApi()
    {
        return myApi;
    }
}
