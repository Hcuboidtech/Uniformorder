package com.uniformorder.uniformorderr.retrofit;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.uniformorder.uniformorderr.activities.UserPreference;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class APIClient {
    private static Retrofit retrofit = null;

    // BASE_URL="http://hcuboidtech.com/godavari/public/api/v1/"; // old
    private static final String BASE_URL="https://www.hcuboidtech.com/godavari/public/api/v1/";
    private Context context;


    public static Retrofit getClient(Context context) {

      /*  HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor)*/

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @NonNull
                    @Override
                    public Response intercept(@NonNull Chain chain) throws IOException {
                        Request original = chain.request();

                        String tokken=UserPreference.getInstance(context).getToken();

                        Log.d("logg1",tokken);

                        Request request = original.newBuilder()
                                .header("Authorization","Bearer "+tokken)
                                .build();

                        return chain.proceed(request);
                    }
                })
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS).build();



        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();


        return retrofit;
    }
}
