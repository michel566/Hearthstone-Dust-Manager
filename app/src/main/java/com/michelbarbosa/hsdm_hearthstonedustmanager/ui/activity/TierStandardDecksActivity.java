package com.michelbarbosa.hsdm_hearthstonedustmanager.ui.activity;

import android.os.Bundle;

import com.michelbarbosa.hsdm_hearthstonedustmanager.R;

public class TierStandardDecksActivity extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayoutContent(R.layout.activity_tstandarddecks);
        setToolbarTitle(R.string.title_tierStandardDecks);
    }
}
