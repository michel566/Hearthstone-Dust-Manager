package com.michelbarbosa.hdm_hearthstone_dust_manager.ui.activities

import android.os.Bundle
import com.michelbarbosa.hdm_hearthstone_dust_manager.R
import com.michelbarbosa.hdm_hearthstone_dust_manager.utils.remoteconfig.RemoteConfigCompleteListener
import com.michelbarbosa.hdm_hearthstone_dust_manager.utils.remoteconfig.RemoteConfigUtils
import kotlinx.android.synthetic.main.activity_yourdecks.*
import michel566.androidmodules.lightdialog.DialogType
import michel566.androidmodules.lightdialog.LightDialog

/**
 * Created by Michel Barbosa on 29/07/2021.
 */
class YourDecksActivity : MainActivity(), RemoteConfigCompleteListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setLayoutContent(R.layout.activity_yourdecks)
        setToolbarTitle(R.string.title_yourDecks)

        initialize()
        setupWidgets()
    }

    private fun initialize() {
        RemoteConfigUtils.startCallback(this)
    }

    private fun setupWidgets() {
        bt_yourdecks_createDeck.setOnClickListener {
            gotoCreateDeckActivity()
        }
    }

    override fun onComplete() {
        val checkDialogUpdate = LightDialog(
            this,
            "current version - " + RemoteConfigUtils.getCurrentVersion() + "\n"
                    + "updateURL - " + RemoteConfigUtils.getUpdateUrl() + "\n"
                    + "updateRequired - " + RemoteConfigUtils.getUpdateRequired() + "\n"
                    + "dataReloadRequired - " + RemoteConfigUtils.getDataReloadRequired(),
            DialogType.INFO, false
        )
        checkDialogUpdate.show()
        checkDialogUpdate.onOptionConfirmClickListener({
            checkDialogUpdate.dismiss()
        }, "OK")
    }
}