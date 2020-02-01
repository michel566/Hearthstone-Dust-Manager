package com.michelbarbosa.hsdm_hearthstonedustmanager.data.network;

import androidx.annotation.NonNull;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {

    private static Retrofit retrofit;
    private static final String BASE_URL = "https://omgvamp-hearthstone-v1.p.rapidapi.com/";
    private static final String API_HOST = "omgvamp-hearthstone-v1.p.rapidapi.com";
    private static final String API_KEY = "YZaoaf9GcVmsh3lobS37cJ9Utd0op1eNl3fjsnbZ3RfqnDy8eA";

    public static Retrofit getRetrofitInstance() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(@NonNull Chain chain) throws IOException {
                Request original = chain.request();
                Request request = original.newBuilder()
                        .header("x-rapidapi-host", API_HOST)
                        .header("x-rapidapi-key", API_KEY)
                        .method(original.method(), original.body())
                        .build();

                return chain.proceed(request);
            }
        })
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build();

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
