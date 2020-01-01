package com.michelbarbosa.hsdm_hearthstonedustmanager.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.michelbarbosa.hsdm_hearthstonedustmanager.R;

public class CreateDeckActivity extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayoutContent(R.layout.activity_createdeck);
        setToolbarTitle(R.string.title_createDeck);

        Button btCreateDeck = findViewById(R.id.bt_createdeck_create);
        btCreateDeck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoDeckBuilderActivity(CreateDeckActivity.this);
            }
        });

    }

}
