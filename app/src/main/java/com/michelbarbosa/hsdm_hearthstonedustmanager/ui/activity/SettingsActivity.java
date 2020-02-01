package com.michelbarbosa.hsdm_hearthstonedustmanager.ui.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.michelbarbosa.hsdm_hearthstonedustmanager.BuildConfig;
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
    private static String[] defaultStereotypeList;
    private static String[] defaultCollectionSet;
    private RecyclerView recyclerView;
    private static final String STATE_LIST = "state_list";
    static final String STEREOTYPE_KEY = "stereotype_key";
    static final String COLLECTION_KEY = "collection_key";
    private List<String> stereotypeList;
    private static int stereotypeCount = 0;

    StereotypeRecyclerClickListener listener = new StereotypeRecyclerClickListener() {
        @Override
        public void onClick(View v, int position) {
            stereotypeAdapter.removeStereotype(position);
            stereotypeList = stereotypeAdapter.getList();
            SharedPreferencesUtil.removeStringToSharedPreferences(editorSharedPref, sharedPreferences,
                    STEREOTYPE_KEY, position, stereotypeList.size());
            restoreStereotypeListPref(stereotypeList);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayoutContent(R.layout.activity_settings);
        setToolbarTitle(R.string.title_settings);
        setDefaultData();
        setViews();

        if (savedInstanceState != null) {
            stereotypeList = savedInstanceState.getStringArrayList(STATE_LIST);
        }
        setCreateStereotypeList();
        setTooltipDialogTextViewers();
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

    private void setDefaultData() {
        defaultStereotypeList = getResources().getStringArray(R.array.array_stereotype);
        defaultCollectionSet = getResources().getStringArray(R.array.array_sets);
    }

    private void setViews() {
        final EditText edCreateStereotype = findViewById(R.id.ed_settings_stereotypeCreate);
        ImageView ivAddStereotype = findViewById(R.id.im_settings_stereotypeAdd);
        TextView tvClearPreferences = findViewById(R.id.tv_settings_clearPreferences);
        TextView tvVersionInfo = findViewById(R.id.tv_settings_VersionInfo);

        tvVersionInfo.setText(BuildConfig.VERSION_NAME);

        recyclerView = findViewById(R.id.rView_cardStereotype);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        UIUtil.iconFillColor(this, ivAddStereotype, R.drawable.ic_note_add, R.color.colorPrimary);
        ivAddStereotype.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String stereotype = edCreateStereotype.getText().toString();
                if (!TextUtils.isEmpty(stereotype)) {
                    edCreateStereotype.clearComposingText();
                    stereotypeCount = stereotypeAdapter.getList().size();
                    stereotypeAdapter.addStereotype(stereotype);
                    SharedPreferencesUtil.setStringToSharedPreferences(editorSharedPref,
                            STEREOTYPE_KEY, stereotypeCount, stereotype);
                }
            }
        });

        tvClearPreferences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final CustomDialog dialog = new CustomDialog(SettingsActivity.this,
                        getResources().getString(R.string.msg_clearPreferences), DialogType.ALERT, false);
                dialog.show();
                UIUtil.showDialogOption(dialog,
                        getResources().getString(R.string.dialog_bt_yes),
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                                clearAllPreferences();
                                restoreAllPreferences();
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

        setSeekBarViews();
    }

    private void setSeekBarViews() {
        SeekBar seekClassicSet = findViewById(R.id.seek_classicSetWeight);
        SeekBar seekStdLastSet = findViewById(R.id.seek_lastSetWeight);
        SeekBar seekStdSecondLastSet = findViewById(R.id.seek_secondlastSetWeight);
        SeekBar seekStdThirdLastSet = findViewById(R.id.seek_thirdlastSetWeight);
        SeekBar seekStdFourthLastSet = findViewById(R.id.seek_fourthlastSetWeight);
        SeekBar seekStdFifthLastSet = findViewById(R.id.seek_fifthlastSetWeight);
        SeekBar seekStdSixthLastSet = findViewById(R.id.seek_sixthlastSetWeight);
        SeekBar seekWildSet = findViewById(R.id.seek_WildSetWeight);
        SeekBar seekBasicType = findViewById(R.id.seek_setBasicType);
        SeekBar seekNeutralType = findViewById(R.id.seek_setNeutralType);
        SeekBar seekClassType = findViewById(R.id.seek_setClassType);

        TextView tvClassicSet = findViewById(R.id.tv_settings_setClassicSet);
        TextView tvStdLastSet = findViewById(R.id.tv_settings_setLastSet);
        TextView tvStdSecondLastSet = findViewById(R.id.tv_settings_setSecondLastSet);
        TextView tvStdThirdLastSet = findViewById(R.id.tv_settings_setThirdLastSet);
        TextView tvStdFourthLastSet = findViewById(R.id.tv_settings_setFourthLastSet);
        TextView tvStdFifthLastSet = findViewById(R.id.tv_settings_setFifthLastSet);
        TextView tvStdSixthLastSet = findViewById(R.id.tv_settings_setSixthLastSet);
        TextView tvWildSet = findViewById(R.id.tv_settings_setWildSet);
        TextView tvBasicType = findViewById(R.id.tv_settings_setBasicType);
        TextView tvNeutralType = findViewById(R.id.tv_settings_setNeutralType);
        TextView tvClassType = findViewById(R.id.tv_settings_setClassType);

        trackValue(seekClassicSet, tvClassicSet, R.string.tv_settings_setClassicSet, null, 7);
        trackValue(seekStdLastSet, tvStdLastSet, 0, defaultCollectionSet[0], 2);
        trackValue(seekStdSecondLastSet, tvStdSecondLastSet, 0, defaultCollectionSet[1], 6);
        trackValue(seekStdThirdLastSet, tvStdThirdLastSet, 0, defaultCollectionSet[2], 5);
        trackValue(seekStdFourthLastSet, tvStdFourthLastSet, 0, defaultCollectionSet[3], 1);
        trackValue(seekStdFifthLastSet, tvStdFifthLastSet, 0, defaultCollectionSet[4], 9);
        trackValue(seekStdSixthLastSet, tvStdSixthLastSet, 0, defaultCollectionSet[5], 6);
        trackValue(seekWildSet, tvWildSet, R.string.tv_settings_setWildSet, null, 9);
        trackValue(seekBasicType, tvBasicType, R.string.tv_settings_setBasicType, null, 3);
        trackValue(seekNeutralType, tvNeutralType, R.string.tv_settings_setNeutralType, null, 1);
        trackValue(seekClassType, tvClassType, R.string.tv_settings_setClassType, null, 6);
    }

    private void trackValue(final SeekBar seekBar, final TextView textView,
                            final int resourceValue, final String text, final int defaultValue) {
        seekBar.setProgress(defaultValue);
        UIUtil.setTextViewWithValue(SettingsActivity.this, textView, resourceValue, text, defaultValue);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                UIUtil.setTextViewWithValue(SettingsActivity.this, textView, resourceValue, text, i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void setCreateStereotypeList() {
        setSharedPreferences();
        stereotypeList = new ArrayList<>();
        stereotypeList = SharedPreferencesUtil.getListToSharedPreferences(sharedPreferences, STEREOTYPE_KEY, 0);

        if (stereotypeList.size() > 0) {
            stereotypeAdapter = new StereotypeAdapter(getLayoutInflater(), listener, stereotypeList);
        } else {
            setDefaultStereotypeList();
        }
        recyclerView.setAdapter(stereotypeAdapter);
    }

    private void setTooltipDialogTextViewers() {
        final List<TextView> textViews = new ArrayList<>();
        TextView tvAppPreferences = findViewById(R.id.tv_settings_preferences);
        TextView tvSetWeight = findViewById(R.id.tv_settings_setWeight);
        TextView tvCardWeight = findViewById(R.id.tv_settings_cardTypeWeight);
        textViews.add(0, tvAppPreferences);
        textViews.add(1, tvSetWeight);
        textViews.add(2, tvCardWeight);

        final List<String> staticTooltipTextList = new ArrayList<>();
        staticTooltipTextList.add(0, getResources().getString(R.string.msg_tooltip_appPreferences));
        staticTooltipTextList.add(1, getResources().getString(R.string.msg_tooltip_setWeight));
        staticTooltipTextList.add(2, getResources().getString(R.string.msg_tooltip_cardWeight));

        UIUtil.setTextViewToTooltipDialog(SettingsActivity.this, staticTooltipTextList, textViews);
    }

    private void setDefaultStereotypeList() {
        stereotypeCount = defaultStereotypeList.length;
        SharedPreferencesUtil.setListToSharedPreferences(editorSharedPref, STEREOTYPE_KEY, 0,
                new ArrayList<>(Arrays.asList(defaultStereotypeList)));
    /*    SharedPreferencesUtil.setListToSharedPreferences(editorSharedPref, COLLECTION_KEY, 0,
                new ArrayList<>(Arrays.asList(defaultCollectionSet)));

     */
        stereotypeAdapter = new StereotypeAdapter(getLayoutInflater(), listener,
                SharedPreferencesUtil.getListToSharedPreferences(sharedPreferences, STEREOTYPE_KEY, 0));
    }

    private void clearAllPreferences() {
        stereotypeAdapter.removeAllStereotypes();
        SharedPreferencesUtil.clearPreferences(sharedPreferences, editorSharedPref, STEREOTYPE_KEY);
        getSavedStateRegistry().unregisterSavedStateProvider(STATE_LIST);
    }

    private void restoreAllPreferences() {
        restoreStereotypeListPref(new ArrayList<>(Arrays.asList(defaultStereotypeList)));
    }

    private void restoreStereotypeListPref(List<String> stereotypeList) {
        SharedPreferencesUtil.setListToSharedPreferences(editorSharedPref, STEREOTYPE_KEY, 0, stereotypeList);
        stereotypeAdapter.setList(stereotypeList);
    }

}