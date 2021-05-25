package com.kychan.mlog

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import com.kychan.mlog.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    private lateinit var remoteConfig: FirebaseRemoteConfig
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash)
        binding.lifecycleOwner = this

        remoteConfig = Firebase.remoteConfig
        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = 3600
        }
        remoteConfig.setConfigSettingsAsync(configSettings)

        remoteConfig.setDefaultsAsync(R.xml.remote_config_defaults)
        fetchWelcome()
    }

    private fun fetchWelcome() {
        val remoteAppVersion = remoteConfig.getString(KEY_LATEST_VERSION)

        remoteConfig.fetchAndActivate()
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    showUpdateDialog()
                } else {
                    Toast.makeText(this, "Fetch failed", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun showUpdateDialog(){
        val updateDialog = MaterialAlertDialogBuilder(this)
            .setTitle(getString(R.string.dialog_update_title))
            .setMessage(getString(R.string.dialog_update_sub_title))
            .setCancelable(false)
            .setNegativeButton(getString(R.string.later)) { _, _ ->
                // Respond to negative button press
            }
            .setPositiveButton(getString(R.string.update)) { _, _ ->
                val intent = Intent(Intent.ACTION_VIEW)
                    .setData(Uri.parse("market://details?id=" + BuildConfig.APPLICATION_ID))

                startActivity(intent)
            }
            .create()

        updateDialog.show()
    }

    companion object {
        private const val TAG = "SplashActivity"

        private const val KEY_LATEST_VERSION = "latest_version"
    }
}