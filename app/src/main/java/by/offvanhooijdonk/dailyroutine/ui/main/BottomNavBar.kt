package by.offvanhooijdonk.dailyroutine.ui.main

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
import by.offvanhooijdonk.dailyroutine.R
import by.offvanhooijdonk.dailyroutine.ui.theme.DailyRoutineTheme

@Composable
fun BottomNavBar(currentRoute: String, onClick: (route: String) -> Unit) {
    NavigationBar {
        NavBarItem.entries.forEach { item ->
            NavigationBarItem(
                selected = currentRoute.startsWith(item.navRoute), // todo split current route by delimiter and take at index 0
                icon = { Icon(painter = painterResource(item.iconRes), contentDescription = null) },
                label = { Text(text = stringResource(item.titleRes)) },
                onClick = { onClick(item.navRoute) },
            )
        }

        FloatingActionButton(onClick = { }) {
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
    //MORE(R.drawable.ic_more, R.string.nav_title_more, NavVMScreen.TimeLineList.route),
}

@Preview(showBackground = true)
@Composable
private fun Preview_BottomNavBar() {
    DailyRoutineTheme {
        BottomNavBar(currentRoute = NavVMScreen.TimeLineList.route) {}
    }
}