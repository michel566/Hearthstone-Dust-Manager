package com.michelbarbosa.hsdm_hearthstonedustmanager.ui.activity;

import android.os.Bundle;

import com.michelbarbosa.hsdm_hearthstonedustmanager.R;

public class TierWildDecksActivity extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayoutContent(R.layout.activity_twilddecks);
        setToolbarTitle(R.string.title_tierWildDecks);
    }
}
