package com.michelbarbosa.hsdm_hearthstonedustmanager.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.widget.AppCompatSpinner;

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

        AppCompatSpinner spStereotype = findViewById(R.id.spPopup_createdeck_stereotype);
        /*
        List<String> list = SharedPreferencesUtil.getList(sharedPreferences, STEREOTYPE_KEY, 0);
        if(list.size() <= 0){
            ArrayAdapter<String> arrayAdapterStereotype = new ArrayAdapter<>(this,
                    android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.array_stereotype));
            spStereotype.setAdapter(arrayAdapterStereotype);
        } else {
            ArrayAdapter<String> arrayAdapterStereotype = new ArrayAdapter<>(this,
                    android.R.layout.simple_spinner_dropdown_item, list);
            spStereotype.setAdapter(arrayAdapterStereotype);
        }

         */

    }

}
