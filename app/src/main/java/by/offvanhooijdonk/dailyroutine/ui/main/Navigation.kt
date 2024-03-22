package by.offvanhooijdonk.dailyroutine.ui.main

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import by.offvanhooijdonk.dailyroutine.ui.timeline.TimelineListScreen
import by.offvanhooijdonk.dailyroutine.ui.timeline.TimelineListViewModel
import org.koin.androidx.compose.navigation.koinNavViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = NavVMScreen.TimeLineList.route) {
        composableVM(screen = NavVMScreen.TimeLineList)
        /*composable(route = NavDestinations.TERMLESS_LIST.route) {

        }
        composable(route = NavDestinations.MORE.route) {

        }*/
    }
}

sealed interface NavVMScreen<V: ViewModel> {
    val route: String

    @Composable fun Composable(vm: V)

    data object TimeLineList : NavVMScreen<TimelineListViewModel> {
        override val route: String = "timeline_list"

        @Composable
        override fun Composable(vm: TimelineListViewModel) {
            TimelineListScreen(vm.uiState.collectAsState().value)
        }
    }

    data object TermlessList : NavVMScreen<TimelineListViewModel> {
        override val route: String = "termless_list"

        @Composable
        override fun Composable(vm: TimelineListViewModel) {
            TimelineListScreen(vm.uiState.collectAsState().value) // todo
        }
    }
}

inline fun <reified V: ViewModel> NavGraphBuilder.composableVM(
    screen: NavVMScreen<V>,
    arguments: List<NamedNavArgument> = emptyList(),
    deepLinks: List<NavDeepLink> = emptyList(),
    noinline enterTransition: (@JvmSuppressWildcards
    AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?)? = null,
    noinline exitTransition: (@JvmSuppressWildcards
    AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?)? = null,
    noinline popEnterTransition: (@JvmSuppressWildcards
    AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?)? =
        enterTransition,
    noinline popExitTransition: (@JvmSuppressWildcards
    AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?)? =
        exitTransition,
) {
    composable(
        route = screen.route,
        arguments = arguments,
        deepLinks = deepLinks,
        enterTransition = enterTransition,
        exitTransition = exitTransition,
        popEnterTransition = popEnterTransition,
        popExitTransition = popExitTransition,
    ) {
        screen.Composable(vm = koinNavViewModel<V>())
    }
}

@Deprecated("")
enum class NavDestinations(val route: String) {
    TIMELINE_LIST("timeline_list"),
    TERMLESS_LIST("termless_list"),
    MORE("more_screen"),
}