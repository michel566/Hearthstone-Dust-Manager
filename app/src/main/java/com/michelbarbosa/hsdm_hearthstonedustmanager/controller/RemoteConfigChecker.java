package com.michelbarbosa.hsdm_hearthstonedustmanager.controller;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.michelbarbosa.hsdm_hearthstonedustmanager.utils.Util;

public class RemoteConfigChecker {

    private static final String TAG = RemoteConfigChecker.class.getSimpleName();

    //parametros do remote config
    static final String UPDATE_REQUIRED = "rc_update_required";
    static final String UPDATE_URL = "rc_update_url";
    static final String CURRENT_VERSION = "rc_current_version";
    static final String DATA_RELOAD_REQUIRED = "rc_data_reload_required";

    private Context context;
    private OnUpdateNeededListener onUpdateNeededListener;

    public synchronized static Builder with(@NonNull Context context) {
        return new Builder(context);
    }

    private RemoteConfigChecker(@NonNull Context context,
                                OnUpdateNeededListener onUpdateNeededListener) {
        this.context = context;
        this.onUpdateNeededListener = onUpdateNeededListener;
    }

    private void check() {
        final FirebaseRemoteConfig remoteConfig = FirebaseRemoteConfig.getInstance();
        String currentVersion = remoteConfig.getString(CURRENT_VERSION);
        String appVersion = Util.getAppVersion(context);
        String updateUrl = remoteConfig.getString(UPDATE_URL);
        boolean updateRequired = remoteConfig.getBoolean(UPDATE_REQUIRED);
        boolean dataReloadRequired = remoteConfig.getBoolean(DATA_RELOAD_REQUIRED);

        Log.d(TAG, "------>" + "\n"
                + "currentVersion - " + currentVersion + "\n"
                + "appVersion - " + appVersion + "\n"
                + "updateURL - " + updateUrl + "\n"
                + "updateRequired? - " + updateRequired + "\n"
                + "dataReloadRequired? - " + dataReloadRequired);

        onUpdateNeededListener.onUpdateNeeded(currentVersion, updateUrl, updateRequired, dataReloadRequired);
    }


    public interface OnUpdateNeededListener {
        void onUpdateNeeded(String currentVersion, String updateUrl, boolean updateRequired, boolean dataReloadRequired);
    }

    public static class Builder {
        private Context context;
        private OnUpdateNeededListener onUpdateNeededListener;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder onUpdateNeeded(OnUpdateNeededListener onUpdateNeededListener) {
            this.onUpdateNeededListener = onUpdateNeededListener;
            return this;
        }

        public RemoteConfigChecker build() {
            return new RemoteConfigChecker(context, onUpdateNeededListener);
        }

        public RemoteConfigChecker check() {
            RemoteConfigChecker remoteConfigChecker = build();
            remoteConfigChecker.check();

            return remoteConfigChecker;
        }
    }
}
