package com.michelbarbosa.hsdm_hearthstonedustmanager.data.network.api;

import com.michelbarbosa.hsdm_hearthstonedustmanager.data.network.response.InfoResponse;
import com.michelbarbosa.hsdm_hearthstonedustmanager.data.network.response.SingleCardResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface APIService {

    @GET("cards/{card}")
    Call<List<SingleCardResponse>> getCard(@Path("card") String card);

    @GET("info")
    Call<InfoResponse> getInfo();

}
