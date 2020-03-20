package com.michelbarbosa.hsdm_hearthstonedustmanager.utils;

import android.content.SharedPreferences;

import com.michelbarbosa.hsdm_hearthstonedustmanager.data.domain.SetWeight;
import com.michelbarbosa.hsdm_hearthstonedustmanager.data.domain.TypeWeight;
import com.michelbarbosa.hsdm_hearthstonedustmanager.enums.CardType;

import java.util.ArrayList;
import java.util.List;

public class SharedPreferencesUtil {

    public static final String INDICATOR = "->";
    private static final String ITEM_DIVISOR = ":";

    //SET LIST
    public static void setList(SharedPreferences.Editor editor, final String key, int position, List<String> listData) {
        if (listData.size() > 1) {
            for (position = 0; position < listData.size(); position++) {
                editor.putString(key + INDICATOR + position, listData.get(position));
            }
        } else if (listData.size() == 1) {
            editor.putString(key, listData.get(position));
        }
        editor.apply();
    }

    public static void setListSetWeight(SharedPreferences.Editor editor, final String key, List<SetWeight> setWeightList) {
        if (setWeightList.size() > 0) {
            for (SetWeight item : setWeightList) {
                editor.putString(key + INDICATOR + item.getIndex(), item.getSet() + ITEM_DIVISOR + item.getWeight());
            }
        }
        editor.apply();
    }

    public static void setListTypeWeight(SharedPreferences.Editor editor, final String key, List<TypeWeight> typeWeightList) {
        if (typeWeightList.size() > 0) {
            int index = 0;
            for (TypeWeight item : typeWeightList) {
                editor.putString(key + INDICATOR + index++, item.getType().toString() + ITEM_DIVISOR + item.getWeight());
            }
        }
        editor.apply();
    }



    //SET ITEM TO LIST
    public static void setStringToSharedPreferences(SharedPreferences.Editor editor,
                                                    final String key, int position, String value) {
        editor.putString(key + INDICATOR + position, value);
        editor.apply();
    }

    public static void setSetWeightToSharedPreferences(SharedPreferences.Editor editor,
                                                       final String key, SetWeight setWeight){
        editor.putString(key + INDICATOR + setWeight.getIndex(),
                setWeight.getSet() + ITEM_DIVISOR + setWeight.getWeight());
        editor.apply();

    }

    public static void setTypeWeightToSharedPreferences(SharedPreferences.Editor editor,
                                                       final String key, TypeWeight typeWeight){
        editor.putString(key + INDICATOR + typeWeight.getIndex(),
                typeWeight.getType() + ITEM_DIVISOR + typeWeight.getWeight());
        editor.apply();
    }



    //GET LIST
    public static List<String> getList(SharedPreferences preferences, final String key, int position) {
        List<String> listData = new ArrayList<>();
        if (preferences.contains(key + INDICATOR + position)) {
            while (preferences.contains(key + INDICATOR + position)) {
                listData.add(position, preferences.getString(key + INDICATOR + position, "default" + INDICATOR + position));
                position++;
            }
        }
        return listData;
    }

    public static List<SetWeight> getSetWeightList(SharedPreferences preferences, final String key) {
        List<SetWeight> listData = new ArrayList<>();
        String keyIndicator = key + INDICATOR;
        int i = 0;

        if (preferences.contains(keyIndicator + i)) {
            while (preferences.contains(keyIndicator + i)) {
                if (preferences.contains(keyIndicator + i)) {
                    String item = preferences.getString(keyIndicator + i, "default" + keyIndicator + i);
                    listData.add(i, new SetWeight(i,
                            Util.split(item, ITEM_DIVISOR)[0],
                            Integer.parseInt(Util.split(item, ITEM_DIVISOR)[1]))
                    );
                    i++;
                } else {
                    break;
                }
            }
        }
        return listData;
    }

    public static List<TypeWeight> getListTypeWeight(SharedPreferences preferences, final String key) {
        List<TypeWeight> listData = new ArrayList<>();
        String keyIndicator = key + INDICATOR;
        int i = 0;

        if (preferences.contains(keyIndicator + i)) {
            while (preferences.contains(keyIndicator + i)) {
                if (preferences.contains(keyIndicator + i)) {
                    String item = preferences.getString(keyIndicator + i, "default" + keyIndicator + i);
                    listData.add(i, new TypeWeight(
                            i,
                            CardType.valueOf(Util.split(item, ITEM_DIVISOR)[0]),
                            Integer.parseInt(Util.split(item, ITEM_DIVISOR)[1])
                    ));
                    i++;
                } else {
                    break;
                }
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

    public static void removeToSharedPreferences(SharedPreferences.Editor editor, SharedPreferences preferenceToUpdate,
                                                 final String key, int positionItemRemoved, int positionLastItemList) {
        editor.remove(key + INDICATOR + positionItemRemoved);
        if (preferenceToUpdate.contains(key + INDICATOR + 0)) {
            editor.remove(key + INDICATOR + positionLastItemList);
        }

        editor.commit();
    }

    public static void removeToSharedPreferences(SharedPreferences.Editor editor, SharedPreferences preferenceToUpdate,
                                                 final String key, TypeWeight typeWeight, int sizeList) {
        editor.remove(key + INDICATOR + typeWeight.getIndex());
        if (preferenceToUpdate.contains(key + INDICATOR + 0)) {
            editor.remove(key + INDICATOR + sizeList);
        }

        editor.commit();
    }

}
