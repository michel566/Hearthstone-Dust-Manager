package com.michelbarbosa.hsdm_hearthstonedustmanager.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.michelbarbosa.hsdm_hearthstonedustmanager.R;

public class YourDecksActivity extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayoutContent(R.layout.activity_yourdecks);
        setToolbarTitle(R.string.title_yourDecks);

        Button btCreateDeck = findViewById(R.id.bt_yourdecks_createDeck);
        btCreateDeck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoCreateDeckActivity(YourDecksActivity.this);
            }
        });

    }

}
