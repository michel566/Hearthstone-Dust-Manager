package com.michelbarbosa.hsdm_hearthstonedustmanager.controller;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
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
        firebaseRemoteConfig.setDefaults(remoteConfigDefaults);

        firebaseRemoteConfig.fetch(1)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "remote config is fetched.");
                            firebaseRemoteConfig.activateFetched();
                        }
                    }
                });

    }
}
