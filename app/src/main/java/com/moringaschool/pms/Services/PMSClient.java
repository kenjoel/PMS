package com.moringaschool.pms.Services;

import com.moringaschool.pms.Constants;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PMSClient {
    private static Retrofit retrofit = null;

    public static PMSApi getClient(){
        OkHttpClient.Builder okhttpClient = new OkHttpClient.Builder();

        if(retrofit == null ){
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);

            okhttpClient.addInterceptor(httpLoggingInterceptor);
            okhttpClient.build();
        }
        retrofit = new Retrofit
                .Builder()
                .baseUrl(Constants.P_M_S_Api)
                .client(okhttpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(PMSApi.class);
    }
}
