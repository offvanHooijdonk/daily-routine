package by.offvanhooijdonk.dailyroutine.ui.main

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import kotlin.properties.Delegates

class NavHolder {
    private var navController: NavHostController by Delegates.notNull()

    fun storeNavController(controller: NavHostController) {
        navController = controller
    }

    @Composable
    fun getBackStackEntryAsState() = navController.currentBackStackEntryAsState()

    fun goToRoot(route: String) {
        navController.navigate(route) {
            navController.graph.startDestinationRoute?.let { popUpTo(it) }
            launchSingleTop = true
        }
    }
}