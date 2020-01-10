package com.michelbarbosa.hsdm_hearthstonedustmanager.ui.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.michelbarbosa.hsdm_hearthstonedustmanager.R;
import com.michelbarbosa.hsdm_hearthstonedustmanager.ui.adapters.StereotypeAdapter;
import com.michelbarbosa.hsdm_hearthstonedustmanager.ui.interfaces.StereotypeRecyclerClickListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SettingsActivity extends MainActivity {

    private StereotypeAdapter stereotypeAdapter;
    private final String[] defaultStereotypeList = {"Aggro, Midrange, Control, Tempo"};
    private RecyclerView recyclerView;
    private static final String STATE_LIST = "state_list";
    private ArrayList stereotypeList;

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

        if(savedInstanceState != null){
            stereotypeList = savedInstanceState.getStringArrayList(STATE_LIST);
        }

        setCreateStereotypeList();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putStringArrayList(STATE_LIST, stereotypeAdapter.getList());
    }

    private void setViews() {
        final EditText edCreateStereotype = findViewById(R.id.ed_settings_stereotypeCreate);
        ImageView ivAddStereotype = findViewById(R.id.im_settings_stereotypeAdd);
        recyclerView = findViewById(R.id.rView_cardStereotype);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ivAddStereotype.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String stereotype = edCreateStereotype.getText().toString();
                if (!TextUtils.isEmpty(stereotype)) {
                    stereotypeAdapter.addStereotype(stereotype);
                }
            }
        });
    }

    private void setCreateStereotypeList() {
        if(stereotypeList != null){
            List<String> list = new ArrayList<>();
            list.addAll(stereotypeList);
            stereotypeAdapter = new StereotypeAdapter(getLayoutInflater(), listener, list);
        } else {
            stereotypeAdapter = new StereotypeAdapter(getLayoutInflater(), listener,
                    new ArrayList<>(Arrays.asList(defaultStereotypeList)));
        }
        recyclerView.setAdapter(stereotypeAdapter);
    }

}