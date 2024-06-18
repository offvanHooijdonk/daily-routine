package by.offvanhooijdonk.dailyroutine.ui.nav

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination.Companion.hierarchy
import by.offvanhooijdonk.dailyroutine.R
import by.offvanhooijdonk.dailyroutine.ui.main.MainViewModel
import by.offvanhooijdonk.dailyroutine.ui.theme.DailyRoutineTheme

@Composable
fun BottomNavBar(currentNavEntry: NavBackStackEntry?, onAction: (MainViewModel.Action) -> Unit) {
    NavigationBar {
        NavBarItem.entries.forEach { item ->
            NavigationBarItem(
                selected = currentNavEntry?.destination?.hierarchy?.any { it.route == item.navRoute } ?: false,
                icon = { Icon(painter = painterResource(item.iconRes), contentDescription = null) },
                label = { Text(text = stringResource(item.titleRes)) },
                onClick = { onAction(MainViewModel.Action.OnNavClick(item.navRoute)) },
            )
        }

        FloatingActionButton(onClick = { onAction(MainViewModel.Action.OnAddTaskClick) }) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Add a task")
        }
        Spacer(modifier = Modifier.width(8.dp)) // keep in mind that container also adds space between items
    }
}

private enum class NavBarItem(
    @DrawableRes val iconRes: Int,
    @StringRes val titleRes: Int,
    val navRoute: String,
) {
    TIMELINE(R.drawable.ic_timeline, R.string.nav_title_timeline, NavVMScreen.TimeLineList.route),
    TERMLESS(R.drawable.ic_termless, R.string.nav_title_termless, NavVMScreen.TermlessList.route),
    MORE(R.drawable.ic_more, R.string.nav_title_more, NavVMScreen.MoreScreen.route),
}

@Preview(showBackground = true)
@Composable
private fun Preview_BottomNavBar() {
    DailyRoutineTheme {
        BottomNavBar(null) {}
    }
}