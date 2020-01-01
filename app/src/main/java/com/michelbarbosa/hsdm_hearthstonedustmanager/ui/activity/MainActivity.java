package com.michelbarbosa.hsdm_hearthstonedustmanager.ui.activity;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.michelbarbosa.hsdm_hearthstonedustmanager.R;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolbar();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
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
