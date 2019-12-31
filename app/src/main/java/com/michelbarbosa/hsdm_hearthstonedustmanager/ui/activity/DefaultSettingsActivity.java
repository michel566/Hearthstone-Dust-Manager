package com.michelbarbosa.hsdm_hearthstonedustmanager.ui.activity;

import android.view.MenuItem;

public interface DefaultSettingsActivity {

    void setToolbar();

    void setLayoutContent(int resourceContentView);

    boolean onOptionsItemSelected(MenuItem item);

    void onBackPressed();

}
