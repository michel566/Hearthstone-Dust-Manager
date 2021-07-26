package com.michelbarbosa.hsdm_hearthstonedustmanager.controller;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;
import com.michelbarbosa.hsdm_hearthstonedustmanager.BuildConfig;
import com.michelbarbosa.hsdm_hearthstonedustmanager.R;

import java.util.HashMap;
import java.util.Map;

public class App extends Application {

    private static final String TAG = App.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        final FirebaseRemoteConfig firebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        Map<String, Object> remoteConfigDefaults = new HashMap();
        remoteConfigDefaults.put(RemoteConfigChecker.UPDATE_REQUIRED, false);
        remoteConfigDefaults.put(RemoteConfigChecker.UPDATE_URL, getResources().getText(R.string.url_updatePlayStore));
        remoteConfigDefaults.put(RemoteConfigChecker.CURRENT_VERSION, BuildConfig.VERSION_NAME);
        remoteConfigDefaults.put(RemoteConfigChecker.DATA_RELOAD_REQUIRED, false);
        FirebaseRemoteConfigSettings configSettings = new FirebaseRemoteConfigSettings.Builder()
                .setMinimumFetchIntervalInSeconds(3600)
                .build();
        firebaseRemoteConfig.setConfigSettingsAsync(configSettings);

        firebaseRemoteConfig.fetchAndActivate()
                .addOnCompleteListener(new OnCompleteListener<Boolean>() {
                    @Override
                    public void onComplete(@NonNull Task<Boolean> task) {
                        if (task.isSuccessful()) {
                            boolean updated = task.getResult();
                            Log.d(TAG, "Config params updated: " + updated);
                            Toast.makeText(getBaseContext(), "Fetch and activate succeeded",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getBaseContext(), "Fetch failed",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
}
