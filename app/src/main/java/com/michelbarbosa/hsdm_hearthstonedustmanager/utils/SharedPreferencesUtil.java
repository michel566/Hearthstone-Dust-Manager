package com.michelbarbosa.hsdm_hearthstonedustmanager.utils;

import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;

public class SharedPreferencesUtil {

    public static final String INDICATOR = "->";

    public static void setListToSharedPreferences(SharedPreferences.Editor editor, final String key, int position, List<String> listData) {
        if (listData.size() > 1) {
            for (position = 0; position < listData.size(); position++) {
                editor.putString(key + INDICATOR + position, listData.get(position));
            }
        } else if (listData.size() == 1) {
            editor.putString(key, listData.get(position));
        }
        editor.apply();
    }

    public static void setStringToSharedPreferences(SharedPreferences.Editor editor, final String key, int position, String value) {
        editor.putString(key + INDICATOR + position, value);
        editor.apply();
    }

    public static List<String> getListToSharedPreferences(SharedPreferences preferences, final String key, int position) {
        List<String> listData = new ArrayList<>();
        if (preferences.contains(key + INDICATOR + position)) {
            while (preferences.contains(key + INDICATOR + position)) {
                listData.add(position, preferences.getString(key + INDICATOR + position, "default" + INDICATOR + position));
                position++;
            }
        }

        return listData;
    }

    public static void clearPreferences(SharedPreferences preferences, SharedPreferences.Editor editor, final String key) {
        int i = 0;
        if (preferences.contains(key + INDICATOR + i)) {
            while (preferences.contains(key + INDICATOR + i)) {
                editor.remove(key + INDICATOR + i);
                i++;
            }
            editor.commit();
        }
    }

    public static void removeStringToSharedPreferences(SharedPreferences.Editor editor, SharedPreferences preferenceToUpdate,
                                                       final String key, int positionItemRemoved, int positionLastItemList) {
        editor.remove(key + INDICATOR + positionItemRemoved);
        if (preferenceToUpdate.contains(key + INDICATOR + 0)) {
            editor.remove(key + INDICATOR + positionLastItemList);
        }

        editor.commit();
    }

}
