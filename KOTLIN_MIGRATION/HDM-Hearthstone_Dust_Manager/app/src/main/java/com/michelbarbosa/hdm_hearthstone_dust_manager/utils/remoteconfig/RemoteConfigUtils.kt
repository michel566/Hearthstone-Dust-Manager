package com.michelbarbosa.hdm_hearthstone_dust_manager.utils.remoteconfig

import android.util.Log
import com.google.firebase.ktx.BuildConfig
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings

/**
 * Created by Michel Barbosa on 26/07/2021.
 */
object RemoteConfigUtils {

    private const val TAG = "RemoteConfigUtils"

    private const val UPDATE_REQUIRED = "rc_update_required"
    private const val UPDATE_URL = "rc_update_url"
    private const val CURRENT_VERSION = "rc_current_version"
    private const val DATA_RELOAD_REQUIRED = "rc_data_reload_required"

    private val DEFAULTS: HashMap<String, Any> =
            hashMapOf(
                    UPDATE_REQUIRED to false,
                    UPDATE_URL to "disponibilizar o app na playstore!",
                    CURRENT_VERSION to 0.2,
                    DATA_RELOAD_REQUIRED to false
            )

    private lateinit var remoteConfig: FirebaseRemoteConfig
    fun init() {
        remoteConfig = getFirebaseRemoteConfig();
    }

    var listener: RemoteConfigCompleteListener? = null
    fun startCallback(listener: RemoteConfigCompleteListener) {
        RemoteConfigUtils.listener = listener
    }

    private fun getFirebaseRemoteConfig(): FirebaseRemoteConfig {
        val remoteConfig: FirebaseRemoteConfig = Firebase.remoteConfig

        val configSettings: FirebaseRemoteConfigSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = if (BuildConfig.DEBUG) {
                15
            } else {
                60 * 60
            }
        }

        remoteConfig.apply {
            setConfigSettingsAsync(configSettings)
            setDefaultsAsync(DEFAULTS)
            fetchAndActivate().addOnCompleteListener {
                Log.d(TAG, "Remote config fetch complete")
                listener?.onComplete()
            }
        }
        return remoteConfig
    }

    fun getUpdateRequired(): Boolean = remoteConfig.getBoolean(UPDATE_REQUIRED)
    fun getUpdateUrl(): String = remoteConfig.getString(UPDATE_URL)
    fun getCurrentVersion(): Double = remoteConfig.getDouble(CURRENT_VERSION)
    fun getDataReloadRequired(): Boolean = remoteConfig.getBoolean(DATA_RELOAD_REQUIRED)

}


/*

how to do implement a callback call in kotlin:

1. Define your interface:
interface OnClickListenerInterface {
    fun onClick()
}
2. Inside the class that will trigger the "onClick" callback i.e. "CircleShape" for your case:
Create a nullable variable.

var listener: OnClickListenerInterface? = null
Declare a function to initialise the variable above.

fun initOnClickInterface(listener: OnClickListenerInterface){
        this.listener = listener
    }
At the point where you want to trigger the "onClick" callback:

mCircleShape.setOnClickListener(View.OnClickListener {
      if (listener == null) return@OnClickListener
      listener?.onClick() // Trigger the call back
    })
3. Inside the activity where you want to receive the "onClick" callback:
Make the activity implement the OnClickListenerInterface, then create an object of your CircleShape class.

class Activity : AppCompatActivity(), OnClickListenerInterface {
    val mCircleShape = CircleShape()
    // ...other stuff
Inside the onCreate function of this activity, initialise your interface using the initOnClickInterface function we created in the CircleShape class.

mCircleShape.initOnClickListenerInterface(this)
Then finish by overriding the onClick method of our interface by adding the code below in the activity.

override fun onClick() {
        // Callback received successfully. Do your stuff here
    }
The above steps worked for me.

As I said, in case of any issues with my coding, I'm a learner too 😎.

Cheers!

credits: https://stackoverflow.com/questions/47499891/how-i-can-use-callback-in-kotlin

 */