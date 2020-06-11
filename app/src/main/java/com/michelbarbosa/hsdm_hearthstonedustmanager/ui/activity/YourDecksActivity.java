package com.michelbarbosa.hsdm_hearthstonedustmanager.ui.activity;

import android.app.AlertDialog;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.michelbarbosa.hsdm_hearthstonedustmanager.R;
import com.michelbarbosa.hsdm_hearthstonedustmanager.controller.RemoteConfigChecker;
import com.michelbarbosa.hsdm_hearthstonedustmanager.enums.DialogType;
import com.michelbarbosa.hsdm_hearthstonedustmanager.ui.components.CustomDialog;
import com.michelbarbosa.hsdm_hearthstonedustmanager.utils.UIUtil;

public class YourDecksActivity extends MainActivity implements RemoteConfigChecker.OnUpdateNeededListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayoutContent(R.layout.activity_yourdecks);
        setToolbarTitle(R.string.title_yourDecks);
        onStartApplication();

        Button btCreateDeck = findViewById(R.id.bt_yourdecks_createDeck);
        btCreateDeck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoCreateDeckActivity(YourDecksActivity.this);
            }
        });
    }

    public void onStartApplication() {
        final AlertDialog progressDialog = UIUtil.progressDialog(this, "Verificando atualizações...");
        progressDialog.show();
        RemoteConfigChecker.with(YourDecksActivity.this).onUpdateNeeded(YourDecksActivity.this).check();
        progressDialog.dismiss();
    }

    @Override
    public void onUpdateNeeded(String currentVersion, String updateUrl, boolean updateRequired, boolean dataReloadRequired) {
        final CustomDialog testeDialog = new CustomDialog(this,
                "currentVersion -" + currentVersion + "\n"
                + "updateURL - " + updateUrl + "\n"
                + "updateRequired? - " + updateRequired + "\n"
                + "dataReloadRequired? - " + dataReloadRequired
                , DialogType.INFO
                ,false
        );
        testeDialog.show();
        testeDialog.onOptionConfirmClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testeDialog.dismiss();
            }
        }, "OK");
    }
}
