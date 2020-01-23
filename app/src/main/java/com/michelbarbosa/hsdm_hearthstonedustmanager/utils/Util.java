package com.michelbarbosa.hsdm_hearthstonedustmanager.utils;

import android.content.Context;
import android.os.Build;

public class Util {

    public static boolean checkMinimalAPI(int buildVersionCode) {
        return Build.VERSION.SDK_INT >= buildVersionCode;
    }

    public static String setFormatString(Context context, int resourceString, int value) {
        return context.getString(resourceString, value);
    }

}
