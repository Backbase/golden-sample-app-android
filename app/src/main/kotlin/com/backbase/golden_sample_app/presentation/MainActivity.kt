package com.backbase.golden_sample_app.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.backbase.android.identity.journey.authentication.initAuthenticationJourney
import com.backbase.android.identity.journey.authentication.stopAuthenticationJourney
import com.backbase.golden_sample_app.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
//        initAuthenticationJourney()

        // super.onCreate() should be called after the Authentication Journey is initialised.
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onDestroy() {
        super.onDestroy()

        // Only stop the Authentication Journey if this is not a configuration change.
        if (!isChangingConfigurations) {
//            stopAuthenticationJourney()
        }
    }
}