package com.michelbarbosa.hsdm_hearthstonedustmanager.presenters;

import com.michelbarbosa.hsdm_hearthstonedustmanager.data.domain.SetWeight;
import com.michelbarbosa.hsdm_hearthstonedustmanager.data.network.response.InfoResponse;

import java.util.ArrayList;
import java.util.List;

class HearthstoneMapper {

    static List<SetWeight> listSetWeightMapper(InfoResponse response){
        List<SetWeight> setWeightList = new ArrayList<>();
        int index = 0;

        for (String item: response.getStandard()) {
            if((!item.equals("Basic"))){
                setWeightList.add(new SetWeight(index++, item, 0));
            }
        }
        return setWeightList;
    }

}
