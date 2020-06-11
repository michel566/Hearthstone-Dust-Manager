package com.michelbarbosa.hsdm_hearthstonedustmanager.presenters;

import android.content.Context;

import com.michelbarbosa.hsdm_hearthstonedustmanager.data.domain.SetWeight;
import com.michelbarbosa.hsdm_hearthstonedustmanager.data.network.response.InfoResponse;
import com.michelbarbosa.hsdm_hearthstonedustmanager.data.network.response.SingleCardResponse;

import java.util.List;

public interface HearthstoneContracts {

    interface presenterView {
        interface loadSingleCard extends presenterView{
            void successOnLoadSingleCard(SingleCardResponse response);
            void failureOnLoadSingleCard(String messageFailure);
        }

        interface loadInfo extends presenterView{
            void successOnLoadStandardSet(List<SetWeight> setWeightList);
            void failureOnStandardSet(String messageFailure);
        }
    }

    interface IHearthstonePresenter {
        void getSingleCard(Context context, String input);
        void getStandardSets(Context context);
    }

}
