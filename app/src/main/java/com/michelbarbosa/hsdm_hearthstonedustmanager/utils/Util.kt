package com.michelbarbosa.hsdm_hearthstonedustmanager.utils

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log

object Util {
    fun checkMinimalAPI(buildVersionCode: Int): Boolean {
        return Build.VERSION.SDK_INT >= buildVersionCode
    }

    fun split(fullText: String, divisor: String?): Array<String> {
        val pos = fullText.indexOf(divisor!!)
        return if (pos > 0) {
            val text1 = fullText.substring(0, pos)
            val text2 = fullText.substring(pos + 1)
            arrayOf(text1, text2)
        } else {
            arrayOf(fullText, "")
        }
    }

    @JvmStatic
    fun getAppVersion(context: Context): String {
        var result = ""
        try {
            result = context.packageManager
                    .getPackageInfo(context.packageName, 0).versionName
            result = result.replace("[a-zA-Z]|-".toRegex(), "")
        } catch (e: PackageManager.NameNotFoundException) {
            Log.e(context.packageName, e.message!!)
        }
        return result
    }
}