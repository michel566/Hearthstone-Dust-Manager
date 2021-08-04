package com.michelbarbosa.hsdm_hearthstonedustmanager.ui.activity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.michelbarbosa.hsdm_hearthstonedustmanager.R;
import com.michelbarbosa.hsdm_hearthstonedustmanager.enums.DialogType;
import com.michelbarbosa.hsdm_hearthstonedustmanager.ui.components.CustomDialog;

public class MainActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            setToolbar();
        }
        setBottomNavigationView();
    }

    private void setBottomNavigationView() {
        BottomNavigationView bottomNavigation = findViewById(R.id.navigationView);
        bottomNavigation.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {
            gotoSettingsActivity(this);
            return true;
        } else if (item.getItemId() == R.id.action_testes) {
            gotoShowCardsActivity(this);
            return true;
        } else {
            super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_yourDecks:
                gotoYourDecksActivity(this);
                return true;
            case R.id.action_tierStandardDecks:
                gotoTierStandardDecksActivity(this);
                return true;
            case R.id.action_tierWildDecks:
                gotoTierWildDecksActivity(this);
                return true;
            default:
                super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }

    protected void failedInUpdateData(Context context){
        final CustomDialog dialog = new CustomDialog(
                context, getText(R.string.msg_failed_in_synchronize_data).toString(),
                DialogType.ALERT, false);
        dialog.show();
        dialog.onOptionConfirmClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        }, getText(R.string.msg_failed_in_synchronize_data).toString());
    }

}
