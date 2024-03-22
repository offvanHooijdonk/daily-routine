package by.offvanhooijdonk.dailyroutine.ui.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController

@Composable
fun MainScreen(viewModel: MainViewModel, navHolder: NavHolder) {
    val navController = rememberNavController()
    navHolder.storeNavController(navController)
    Scaffold(
        bottomBar = { BottomNavBar(navHolder.getBackStackEntryAsState().value) { navHolder.goToRoot(it) } }
    ) { contentPaddings ->
        Surface(modifier = Modifier.padding(contentPaddings)) {
            AppNavigation(navController)
        }
    }
}