package com.michelbarbosa.hdm_hearthstone_dust_manager.controller

import android.app.Application
import com.michelbarbosa.hdm_hearthstone_dust_manager.utils.remoteconfig.RemoteConfigUtils

/**
 * Created by Michel Barbosa on 29/07/2021.
 */
class App : Application(){

    override fun onCreate() {
        super.onCreate()
        RemoteConfigUtils.init()
    }
}