package com.backbase.android.retail

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.backbase.android.design.theme.PreviewTheme
import com.backbase.android.design.theme.Theme
import com.backbase.android.retail.authorization.AuthenticationJourney
import com.backbase.android.retail.contacts.ContactsJourney

class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            PreviewTheme {
                val navController: NavHostController = rememberNavController()
                NavHost(navController, startDestination = "authentication") {
                    composable("authentication") {
                        AuthenticationJourney(
                            onAuthenticated = {
                                navController.navigate("app")
                            }
                        )
                    }
                    composable("app") {
                        ContactsJourney()
                    }
                }
            }
        }
    }
}
