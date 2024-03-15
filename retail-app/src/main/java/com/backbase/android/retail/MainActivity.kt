package com.backbase.android.retail

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.fragment.app.FragmentActivity
import com.backbase.android.design.theme.PreviewTheme
import com.backbase.android.retail.authorization.AuthenticationJourney

class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PreviewTheme {
                AuthenticationJourney()
            }
        }
    }
}
