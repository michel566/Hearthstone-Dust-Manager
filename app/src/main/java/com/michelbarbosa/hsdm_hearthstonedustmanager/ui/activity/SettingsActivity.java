package com.michelbarbosa.hsdm_hearthstonedustmanager.ui.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.michelbarbosa.hsdm_hearthstonedustmanager.R;
import com.michelbarbosa.hsdm_hearthstonedustmanager.enums.DialogType;
import com.michelbarbosa.hsdm_hearthstonedustmanager.ui.adapters.StereotypeAdapter;
import com.michelbarbosa.hsdm_hearthstonedustmanager.ui.components.CustomDialog;
import com.michelbarbosa.hsdm_hearthstonedustmanager.ui.interfaces.StereotypeRecyclerClickListener;
import com.michelbarbosa.hsdm_hearthstonedustmanager.utils.SharedPreferencesUtil;
import com.michelbarbosa.hsdm_hearthstonedustmanager.utils.UIUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SettingsActivity extends MainActivity {

    private StereotypeAdapter stereotypeAdapter;
    private static final String[] defaultStereotypeList = {"Aggro", "Midrange", "Control", "Tempo"};
    private RecyclerView recyclerView;
    private static final String STATE_LIST = "state_list";
    private static final String STEREOTYPE_KEY = "stereotype_key";
    private List<String> stereotypeList;
    private static int stereotypeCount = 0;

    StereotypeRecyclerClickListener listener = new StereotypeRecyclerClickListener() {
        @Override
        public void onClick(View v, int position) {
            stereotypeAdapter.removeStereotype(position);
            SharedPreferencesUtil.removeStringToSharedPreferences(editorSharedPref, STEREOTYPE_KEY, position);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayoutContent(R.layout.activity_settings);
        setToolbarTitle(R.string.title_settings);
        setViews();

        if (savedInstanceState != null) {
            stereotypeList = savedInstanceState.getStringArrayList(STATE_LIST);
        }
        setCreateStereotypeList();
    }

    @Override
    protected void onStop() {
        super.onStop();
        stereotypeCount = 0;
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putStringArrayList(STATE_LIST, stereotypeAdapter.getList());
    }

    private void setViews() {
        final EditText edCreateStereotype = findViewById(R.id.ed_settings_stereotypeCreate);
        ImageView ivAddStereotype = findViewById(R.id.im_settings_stereotypeAdd);
        TextView tvClearPreferences = findViewById(R.id.tv_settings_clearPreferences);
        recyclerView = findViewById(R.id.rView_cardStereotype);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        UIUtil.iconFillColor(this, ivAddStereotype, R.drawable.ic_note_add, R.color.colorPrimary);
        ivAddStereotype.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String stereotype = edCreateStereotype.getText().toString();
                if (!TextUtils.isEmpty(stereotype)) {
                    edCreateStereotype.clearComposingText();
                    stereotypeAdapter.addStereotype(stereotype);
                    SharedPreferencesUtil.setStringToSharedPreferences(editorSharedPref,
                            STEREOTYPE_KEY, stereotypeCount, stereotype);
                    stereotypeCount++;
                }
            }
        });

        tvClearPreferences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final CustomDialog dialog = new CustomDialog(SettingsActivity.this,
                        getResources().getString(R.string.msg_clearPreferences), DialogType.ALERT, true);
                dialog.show();
                UIUtil.showDialogOption(dialog,
                        getResources().getString(R.string.dialog_bt_yes),
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                                clearAllPreferences();
                            }
                        }, getResources().getString(R.string.dialog_bt_no),
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });
            }
        });
    }

    private void setCreateStereotypeList() {
        setSharedPreferences();
        stereotypeList = new ArrayList<>();
        stereotypeList = SharedPreferencesUtil.getListToSharedPreferences(sharedPreferences, STEREOTYPE_KEY, stereotypeCount);

        if (stereotypeList.size() > 0) {
            stereotypeAdapter = new StereotypeAdapter(getLayoutInflater(), listener, stereotypeList);
        } else {
            stereotypeCount = defaultStereotypeList.length;
            SharedPreferencesUtil.setListToSharedPreferences(editorSharedPref, STEREOTYPE_KEY, 0,
                    new ArrayList<>(Arrays.asList(defaultStereotypeList)));
            stereotypeAdapter = new StereotypeAdapter(getLayoutInflater(), listener,
                    SharedPreferencesUtil.getListToSharedPreferences(sharedPreferences, STEREOTYPE_KEY, stereotypeCount));
        }
        recyclerView.setAdapter(stereotypeAdapter);
    }

    private void clearAllPreferences() {
        stereotypeAdapter.removeAllStereotypes();
        SharedPreferencesUtil.clearPreferences(sharedPreferences, editorSharedPref, STEREOTYPE_KEY);
        getSavedStateRegistry().unregisterSavedStateProvider(STATE_LIST);
    }

}