package com.backbase.android.retail

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.backbase.android.retail.authorization.AuthenticationJourney
import com.backbase.android.retail.contacts.contactsJourney
import com.backbase.android.retail.contacts.contactsScreen
import com.backbase.android.retail.contacts.detailsScreen
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
                navController.navigate("contacts")
            })
        }

        contactsJourney {
            contactsScreen {
                destination = "contacts"

                configuration {
                    title = "Title"
                }

                router {
                    onSearchAction = { flag ->
                        if (flag) {
                            navController.navigate("to-the-moon")
                        } else {
                            navController.navigate("to-the-sun")
                        }
                    }
                    onItemClickedAction = { userName ->
                        navController.navigate("contact-details/$userName")
                    }
                }
            }
            detailsScreen {
                destination = "details"
                configuration {
                    title = "Title"
                }
            }
        }
    }
}