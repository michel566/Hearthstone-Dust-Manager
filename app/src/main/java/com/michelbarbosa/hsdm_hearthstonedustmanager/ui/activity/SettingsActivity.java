package com.michelbarbosa.hsdm_hearthstonedustmanager.ui.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.google.gson.Gson;
import com.michelbarbosa.hsdm_hearthstonedustmanager.R;
import com.michelbarbosa.hsdm_hearthstonedustmanager.ui.adapters.StereotypeAdapter;
import com.michelbarbosa.hsdm_hearthstonedustmanager.ui.interfaces.StereotypeRecyclerClickListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SettingsActivity extends MainActivity {

    private StereotypeAdapter stereotypeAdapter;
    private final String[] defaultStereotypeList = {"Aggro, Midrange, Control, Tempo"};
    private List<String> stereotypeList;

    StereotypeRecyclerClickListener listener = new StereotypeRecyclerClickListener() {
        @Override
        public void onClick(View v, int position) {
            stereotypeAdapter.removeStereotype(position);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayoutContent(R.layout.activity_settings);
        setToolbarTitle(R.string.title_settings);
        setViews();
        setCreateStereotypeList();
    }

    private void setViews() {
        final EditText edCreateStereotype = findViewById(R.id.ed_settings_stereotypeCreate);
        ImageView ivAddStereotype = findViewById(R.id.im_settings_stereotypeAdd);

        ivAddStereotype.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String stereotype = edCreateStereotype.getText().toString();
                if(!TextUtils.isEmpty(stereotype)){
                    stereotypeAdapter.addStereotype(stereotype);
                    storeStereotypePref();
                }
            }
        });
    }

    private void setCreateStereotypeList() {
        setSharedPreferences();
        RecyclerView recyclerView = findViewById(R.id.rView_cardStereotype);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Gson gson = new Gson();
        String jsonText = sharedPreferences.getString("key",null);
        String[] textList = gson.fromJson(jsonText, String[].class);

        if(textList == null){
            textList = defaultStereotypeList;
        }

        stereotypeAdapter = new StereotypeAdapter(getLayoutInflater(), listener,
                new ArrayList<>(Arrays.asList(textList)));
        recyclerView.setAdapter(stereotypeAdapter);
    }

    private void storeStereotypePref(){
        Gson gson = new Gson();
        stereotypeList.addAll(stereotypeAdapter.stereotypeList);
        String jsonText = gson.toJson(stereotypeList);
        editorSharedPref.putString("key", jsonText);
        editorSharedPref.apply();
    }


}