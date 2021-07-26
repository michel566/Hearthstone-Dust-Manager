package com.michelbarbosa.hsdm_hearthstonedustmanager.utils;

import com.michelbarbosa.hsdm_hearthstonedustmanager.data.domain.SetWeight;
import com.michelbarbosa.hsdm_hearthstonedustmanager.data.domain.Stereotype;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MapperUtil {

    public static List<Stereotype> getDefaultStereotype(String... defaultList) {
        List<String> stereotypeNames = new ArrayList<>(Arrays.asList(defaultList));
        List<Stereotype> stereotypeList = new ArrayList<>();
        int i = 0;
        for (String item : stereotypeNames) {
            stereotypeList.add(new Stereotype(i, item));
            i++;
        }
        return stereotypeList;
    }

    public static List<SetWeight> getDefaultSetWeight(int defaultValueAll, String... defaultList){
        List<String> setWeightNames = new ArrayList<>(Arrays.asList(defaultList));
        List<SetWeight> setWeightList = new ArrayList<>();
        int i = 0;
        for (String item: setWeightNames) {
            setWeightList.add(new SetWeight(i, item, defaultValueAll));
            i++;
        }
        return setWeightList;
    }

}
