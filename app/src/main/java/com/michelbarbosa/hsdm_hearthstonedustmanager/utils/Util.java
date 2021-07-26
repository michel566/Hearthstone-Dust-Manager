package com.michelbarbosa.hsdm_hearthstonedustmanager.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;

public class Util {

    public static boolean checkMinimalAPI(int buildVersionCode) {
        return Build.VERSION.SDK_INT >= buildVersionCode;
    }

    public static String[] split(String fullText, String divisor) {
        int pos = fullText.indexOf(divisor);
        if(pos > 0){
            String text1 = fullText.substring(0,pos);
            String text2 = fullText.substring(pos+1);
            return new String[]{text1, text2};
        }
        else{
            return new String[]{fullText, ""};
        }
    }

    public static String getAppVersion(Context context) {
        String result = "";
        try {
            result = context.getPackageManager()
                    .getPackageInfo(context.getPackageName(), 0)
                    .versionName;
            result = result.replaceAll("[a-zA-Z]|-", "");
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(context.getPackageName(), e.getMessage());
        }
        return result;
    }

}
