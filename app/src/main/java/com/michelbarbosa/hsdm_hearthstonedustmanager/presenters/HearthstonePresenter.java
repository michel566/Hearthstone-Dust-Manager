package com.michelbarbosa.hsdm_hearthstonedustmanager.presenters;

import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;

import com.michelbarbosa.hsdm_hearthstonedustmanager.data.network.RetrofitClientInstance;
import com.michelbarbosa.hsdm_hearthstonedustmanager.data.network.api.APIService;
import com.michelbarbosa.hsdm_hearthstonedustmanager.data.network.response.InfoResponse;
import com.michelbarbosa.hsdm_hearthstonedustmanager.data.network.response.SingleCardResponse;
import com.michelbarbosa.hsdm_hearthstonedustmanager.utils.UIUtil;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HearthstonePresenter implements HearthstoneContracts.IHearthstonePresenter {

    private HearthstoneContracts.presenterView.loadSingleCard singleCardview;
    private HearthstoneContracts.presenterView.loadInfo loadStandardSetView;

    public HearthstonePresenter(HearthstoneContracts.presenterView.loadSingleCard view) {
        this.singleCardview = view;
    }

    public HearthstonePresenter(HearthstoneContracts.presenterView.loadInfo loadStandardSetView){
        this.loadStandardSetView = loadStandardSetView;
    }

    @Override
    public void getStandardSets(Context context) {
        APIService service = RetrofitClientInstance.getRetrofitInstance().create(APIService.class);
        Call<InfoResponse> call = service.getInfo();
        final AlertDialog progressDialog = UIUtil.progressDialog(context, "carregando cartas...");
        call.enqueue(new Callback<InfoResponse>() {
            @Override
            public void onResponse(Call<InfoResponse> call, Response<InfoResponse> response) {
                progressDialog.dismiss();
                if (response.body() != null) {
                    loadStandardSetView.successOnLoadStandardSet(HearthstoneMapper.listSetWeightMapper(response.body()));
                }
            }

            @Override
            public void onFailure(Call<InfoResponse> call, Throwable t) {
                progressDialog.dismiss();
                loadStandardSetView.failureOnStandardSet("falha: " + t.getMessage());
            }
        });
    }

    @Override
    public void getSingleCard(Context context, String input) {
        APIService service = RetrofitClientInstance.getRetrofitInstance().create(APIService.class);
        Call<List<SingleCardResponse>> call = service.getCard(input);
        final AlertDialog progressDialog = UIUtil.progressDialog(context, "Carregando cartas...");
        call.enqueue(new Callback<List<SingleCardResponse>>() {
            @Override
            public void onResponse(Call<List<SingleCardResponse>> call, Response<List<SingleCardResponse>> response) {
                progressDialog.dismiss();
                if(response.isSuccessful()){
                    if(response.body() != null){
                        Log.d("RESPONSE ---> ", response.body().toString());
                        singleCardview.successOnLoadSingleCard(response.body().get(0));
                    }else {
                        singleCardview.failureOnLoadSingleCard("response.body() nulo");
                    }
                } else {
                    singleCardview.failureOnLoadSingleCard("código: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<SingleCardResponse>> call, Throwable t) {
                progressDialog.dismiss();
                singleCardview.failureOnLoadSingleCard("ocorreu algum problema: " + t.getMessage());
            }
        });

    }

}
