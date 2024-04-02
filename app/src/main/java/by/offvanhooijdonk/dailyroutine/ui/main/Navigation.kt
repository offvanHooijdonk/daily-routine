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
import by.offvanhooijdonk.dailyroutine.ui.more_screen.MoreScreen
import by.offvanhooijdonk.dailyroutine.ui.more_screen.MoreScreenViewModel
import by.offvanhooijdonk.dailyroutine.ui.termless.TermlessListScreen
import by.offvanhooijdonk.dailyroutine.ui.termless.TermlessListViewModel
import by.offvanhooijdonk.dailyroutine.ui.timeline.TimelineListScreen
import by.offvanhooijdonk.dailyroutine.ui.timeline.TimelineListViewModel
import org.koin.androidx.compose.navigation.koinNavViewModel

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = NavVMScreen.TimeLineList.route) {
        composableVM(screen = NavVMScreen.TimeLineList)
        composableVM(screen = NavVMScreen.TermlessList)
        composableVM(screen = NavVMScreen.MoreScreen)
    }
}

sealed interface NavVMScreen<V: ViewModel> {
    val route: String

    @Composable fun Composable(vm: V)

    data object TimeLineList : NavVMScreen<TimelineListViewModel> {
        override val route: String = "timeline_list"

        @Composable
        override fun Composable(vm: TimelineListViewModel) {
            TimelineListScreen(vm.uiState.collectAsState().value, vm::onAction)
        }
    }

    data object TermlessList : NavVMScreen<TermlessListViewModel> {
        override val route: String = "termless_list"

        @Composable
        override fun Composable(vm: TermlessListViewModel) {
            TermlessListScreen() // todo
        }
    }

    data object MoreScreen : NavVMScreen<MoreScreenViewModel> {
        override val route: String = "more_screen"

        @Composable
        override fun Composable(vm: MoreScreenViewModel) {
            MoreScreen() // todo
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