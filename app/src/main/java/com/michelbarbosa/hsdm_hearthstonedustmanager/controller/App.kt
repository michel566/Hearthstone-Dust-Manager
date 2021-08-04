package com.michelbarbosa.hsdm_hearthstonedustmanager.controller

import android.app.Application
import com.michelbarbosa.hsdm_hearthstonedustmanager.utils.RemoteConfigUtils

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        RemoteConfigUtils.init()
    }
}