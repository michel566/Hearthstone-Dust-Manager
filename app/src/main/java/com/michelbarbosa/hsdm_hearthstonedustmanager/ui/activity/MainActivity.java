package com.michelbarbosa.hsdm_hearthstonedustmanager.ui.activity;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.michelbarbosa.hsdm_hearthstonedustmanager.R;

public class MainActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolbar();
        setBottomNavigationView();
    }

    private void setBottomNavigationView() {
        bottomNavigation = findViewById(R.id.navigationView);
        bottomNavigation.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {
            gotoSettingsActivity(this);
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
}
