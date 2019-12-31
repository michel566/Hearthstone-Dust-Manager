package com.michelbarbosa.hsdm_hearthstonedustmanager.ui.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

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
                Toast.makeText(this, "your decks", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_tierStandardDecks:
                Toast.makeText(this, "tier standart decks", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_tierWildDecks:
                Toast.makeText(this, "tier wild decks", Toast.LENGTH_SHORT).show();
                return true;
            default:
                super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }

}
