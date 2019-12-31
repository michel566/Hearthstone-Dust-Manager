package com.michelbarbosa.hsdm_hearthstonedustmanager.ui.activity;

import android.content.Context;
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

import com.michelbarbosa.hsdm_hearthstonedustmanager.R;


public class BaseActivity extends AppCompatActivity implements DefaultSettingsActivity {

    protected Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
    }

    @Override
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

    @Override
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

    protected void destroyApplication(Context context) {
        if (context != null) {
            finishAffinity();
        }
    }


}
