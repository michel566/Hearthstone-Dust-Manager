package com.michelbarbosa.hsdm_hearthstonedustmanager.ui.activity;

import android.os.Bundle;

import com.michelbarbosa.hsdm_hearthstonedustmanager.R;

public class DeckBuilderActitvity extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayoutContent(R.layout.activity_deckbuilder);
        setToolbarTitle(R.string.title_deckBuilder);
    }
}
