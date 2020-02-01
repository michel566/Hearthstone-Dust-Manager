package com.michelbarbosa.hsdm_hearthstonedustmanager.presenters;

import android.content.Context;

import com.michelbarbosa.hsdm_hearthstonedustmanager.data.network.response.InfoResponse;
import com.michelbarbosa.hsdm_hearthstonedustmanager.data.network.response.SingleCardResponse;

public interface HearthstoneContracts {

    interface presenterView {
        void successOnLoadSingleCard(SingleCardResponse response);
        void failureOnLoadSingleCard(String messageFailure);

        void successOnLoadInfo(InfoResponse response);
        void failureOnLoadInfo(String messageFailure);
    }

    interface IHearthstonePresenter {
        void getSingleCard(Context context, String input);
        void getInfo(Context context);
    }

}
