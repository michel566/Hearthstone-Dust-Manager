package com.michelbarbosa.hsdm_hearthstonedustmanager.ui.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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
import com.michelbarbosa.hsdm_hearthstonedustmanager.callbacks.SetValueWeight;
import com.michelbarbosa.hsdm_hearthstonedustmanager.data.domain.SetWeight;
import com.michelbarbosa.hsdm_hearthstonedustmanager.data.domain.TypeWeight;
import com.michelbarbosa.hsdm_hearthstonedustmanager.enums.CardType;
import com.michelbarbosa.hsdm_hearthstonedustmanager.enums.DialogType;
import com.michelbarbosa.hsdm_hearthstonedustmanager.presenters.HearthstoneContracts;
import com.michelbarbosa.hsdm_hearthstonedustmanager.presenters.HearthstonePresenter;
import com.michelbarbosa.hsdm_hearthstonedustmanager.ui.adapters.StereotypeAdapter;
import com.michelbarbosa.hsdm_hearthstonedustmanager.ui.components.CustomDialog;
import com.michelbarbosa.hsdm_hearthstonedustmanager.ui.interfaces.StereotypeRecyclerClickListener;
import com.michelbarbosa.hsdm_hearthstonedustmanager.utils.UIUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SettingsActivity extends MainActivity implements HearthstoneContracts.presenterView.loadInfo, SetValueWeight{

    protected HearthstoneContracts.IHearthstonePresenter presenter = new HearthstonePresenter(this);

    private StereotypeAdapter stereotypeAdapter;
    private static String[] defaultStereotypeList;
    private static List<SetWeight> setWeightList;
    private static List<TypeWeight> typeWeightList;
    private RecyclerView recyclerView;
    private SetValueWeight setValueWeight;
    private List<String> stereotypeList;
    private static int stereotypeCount = 0;

    StereotypeRecyclerClickListener listener = new StereotypeRecyclerClickListener() {
        @Override
        public void onClick(View v, int position) {
            stereotypeAdapter.removeStereotype(position);
            stereotypeList = stereotypeAdapter.getList();
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

        setSharedPreferences();
        setCreateStereotypeList();
        presenter.getStandardSets(this);

        setTypeWeightList();
        setTooltipDialogTextViewers();
    }

    private void setTypeWeightList() {
        List<TypeWeight> list = new ArrayList<>();
        list.add(new TypeWeight(0, CardType.BASIC, 0));
        list.add(new TypeWeight(1, CardType.CLASS, 5));
        list.add(new TypeWeight(2, CardType.NEUTRAL, 4));

    }

    @Override
    protected void onStop() {
        super.onStop();
        stereotypeCount = 0;
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    private void setDefaultData() {
        defaultStereotypeList = getResources().getStringArray(R.array.array_stereotype);
    }

    private void setViews() {
        final EditText edCreateStereotype = findViewById(R.id.ed_settings_stereotypeCreate);
        ImageView ivAddStereotype = findViewById(R.id.im_settings_stereotypeAdd);
        TextView tvClearPreferences = findViewById(R.id.tv_settings_clearPreferences);
        TextView tvVersionInfo = findViewById(R.id.tv_settings_VersionInfo);

        if (BuildConfig.DEBUG) {
            tvVersionInfo.setText(BuildConfig.VERSION_NAME + " - debug");
        } else {
            tvVersionInfo.setText(BuildConfig.VERSION_NAME);
        }

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
    }

    private void trackValue(final SeekBar seekBar, final TextView textView,
                               final int resourceValue, final SetWeight setWeight){
        seekBar.setProgress(setWeight.getWeight());
        UIUtil.setTextViewWithValue(SettingsActivity.this, textView, resourceValue, setWeight.getSet(), setWeight.getWeight());

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                UIUtil.setTextViewWithValue(SettingsActivity.this, textView, resourceValue, setWeight.getSet(), i);
                setWeight.setWeight(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                setValueWeight.callbackValueSpinnerSelect(setWeight);
            }
        });
    }

    private void trackValue(final SeekBar seekBar, final TextView textView,
                               final int resourceValue, final TypeWeight typeWeight){
        seekBar.setProgress(typeWeight.getWeight());
        UIUtil.setTextViewWithValue(SettingsActivity.this, textView, resourceValue, typeWeight.getType().toString(), typeWeight.getWeight());
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                UIUtil.setTextViewWithValue(SettingsActivity.this, textView, resourceValue, typeWeight.getType().toString(), i);
                typeWeight.setWeight(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                setValueWeight.callbackValueSpinnerSelect(typeWeight);
            }
        });
    }

    @Override
    public void callbackValueSpinnerSelect(SetWeight setWeight) {
    }

    @Override
    public void callbackValueSpinnerSelect(TypeWeight typeWeight) {
    }


    private void setCreateStereotypeList() {
        stereotypeList = new ArrayList<>();
        if (stereotypeList.size() > 0) {
            setDefaultStereotypeList();
        } else {
            stereotypeAdapter = new StereotypeAdapter(getLayoutInflater(), listener, stereotypeList);
        }
        recyclerView.setAdapter(stereotypeAdapter);
    }

    @Override
    public void successOnLoadStandardSet(List<SetWeight> setWeightList) {
    }

    private void testResponseSetWeightList(List<SetWeight> setWeightList) {
        Log.d("testResponseSets", "-----testResponseSets-----");
        for (SetWeight item : setWeightList) {
            Log.d("testResponseSets: ", "index: " + item.getIndex()
                    + "|" + "set: " + item.getSet()
                    + "|" + "weight: " + item.getWeight());
        }
    }

    private void testResponseTypeWeightList(List<TypeWeight> setTypeWeight) {
        Log.d("testResponseSets", "-----testResponseSets-----");
        for (TypeWeight item : setTypeWeight) {
            Log.d("testResponseSets: ", "type: " + item.getType()
                    + "|" + "weight: " + item.getWeight());
        }
    }

    @Override
    public void failureOnStandardSet(String messageFailure) {
        failedInUpdateData(this);
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
    }

    private void clearAllPreferences() {
        stereotypeAdapter.removeAllStereotypes();
    }

    private void restoreAllPreferences() {
        restoreStereotypeListPref(new ArrayList<>(Arrays.asList(defaultStereotypeList)));
    }

    private void restoreStereotypeListPref(List<String> stereotypeList) {
        stereotypeAdapter.setList(stereotypeList);
    }

}