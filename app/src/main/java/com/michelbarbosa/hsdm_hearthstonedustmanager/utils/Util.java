package com.michelbarbosa.hsdm_hearthstonedustmanager.utils;

import android.os.Build;

public class Util {

    public static boolean checkMinimalAPI(int buildVersionCode) {
        return Build.VERSION.SDK_INT >= buildVersionCode;
    }

}
