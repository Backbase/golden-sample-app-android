package com.backbase.android.retail

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.backbase.android.retail.authorization.AuthenticationJourney
import com.backbase.android.retail.contacts.ContactsJourney
import com.backbase.android.retail.workspaceselector.WorkspaceSelectorJourney

@Composable
fun MainNavHost() {
    val navController: NavHostController = rememberNavController()
    NavHost(
        navController,
        startDestination = "authentication"
    ) {
        composable("authentication") {
            AuthenticationJourney(
                onAuthenticated = {
                    navController.navigate("workspace")
                }
            )
        }
        composable("workspace") {
            WorkspaceSelectorJourney(onSuccess = {
                navController.navigate("app")
            })
        }
        composable("app") {
            ContactsJourney()
        }
    }
}