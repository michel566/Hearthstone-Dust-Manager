package com.michelbarbosa.hsdm_hearthstonedustmanager.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.michelbarbosa.hsdm_hearthstonedustmanager.BuildConfig;
import com.michelbarbosa.hsdm_hearthstonedustmanager.R;

public class BaseActivity extends AppCompatActivity {

    protected Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
    }

    protected void setSharedPreferences() {

    }

    public void setToolbar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setOverflowIcon();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            toolbar.setElevation(4);
        }
        enableDisplayShow(false);
    }

    protected void setToolbarTitle(int idResourceTitle) {
        if (idResourceTitle != 0) {
            TextView titleToolbar = findViewById(R.id.titleToolbar);
            String title = getResources().getString(idResourceTitle);
            titleToolbar.setText(title);

            if (title.length() > 23) {
                titleToolbar.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
            } else if (title.length() > 19) {
                titleToolbar.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
            }
        }
    }

    void managerFragmentTransaction(int idContainer, Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        manager.findFragmentById(idContainer);
        FragmentTransaction transaction = manager.beginTransaction();
        if (transaction.isEmpty()) {
            transaction.add(idContainer, fragment);
        } else {
            transaction.replace(idContainer, fragment);
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }

    public void setArrowBackPressed() {
        enableDisplayShow(true);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void enableDisplayShow(boolean isShow) {
        if (toolbar != null) {
            getSupportActionBar().setDisplayShowHomeEnabled(isShow);
            getSupportActionBar().setDisplayShowTitleEnabled(isShow);
        }
    }

    private void setOverflowIcon() {
        Drawable icon = ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_more_vert);
        toolbar.setOverflowIcon(icon);
    }


    public void setLayoutContent(int resourceContentView) {
        RelativeLayout dinamicContent = findViewById(R.id.content);
        View view = getLayoutInflater().inflate(resourceContentView, dinamicContent, false);
        dinamicContent.addView(view);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_testes) {
            if(!BuildConfig.DEBUG){
                item.getActionView().setVisibility(View.GONE);
            } else {
                item.getActionView().setVisibility(View.VISIBLE);
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (getCurrentFocus() != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    protected void clearData() {
    }

    protected void destroyApplication(Context context) {
        if (context != null) {
            finishAffinity();
        }
    }

    //Navegability methods

    protected void gotoYourDecksActivity(Context context) {
        Intent it = new Intent(context, YourDecksActivity.class);
        gotoActivity(it, context);
    }

    protected void gotoTierStandardDecksActivity(Context context) {
        Intent it = new Intent(context, TierStandardDecksActivity.class);
        gotoActivity(it, context);
    }

    protected void gotoTierWildDecksActivity(Context context) {
        Intent it = new Intent(context, TierWildDecksActivity.class);
        gotoActivity(it, context);
    }

    protected void gotoCreateDeckActivity(Context context) {
        Intent it = new Intent(context, CreateDeckActivity.class);
        gotoActivity(it, context);
    }

    protected void gotoDeckBuilderActivity(Context context) {
        Intent it = new Intent(context, DeckBuilderActitvity.class);
        gotoActivity(it, context);
    }

    protected void gotoSettingsActivity(Context context) {
        Intent it = new Intent(context, SettingsActivity.class);
        gotoActivity(it, context);
    }

    protected void gotoShowCardsActivity(Context context) {
        Intent it = new Intent(context, ShowCardsActivity.class);
        gotoActivity(it, context);
    }

    private void gotoActivity(Intent intent, Context context) {
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        context.startActivity(intent);
    }


}
