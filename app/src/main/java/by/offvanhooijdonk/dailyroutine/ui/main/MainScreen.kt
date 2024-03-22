package by.offvanhooijdonk.dailyroutine.ui.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import by.offvanhooijdonk.dailyroutine.ui.theme.DailyRoutineTheme

@Composable
fun MainScreen() {
    Scaffold(
        bottomBar = { BottomNavBar("") {} }
    ) { contentPaddings ->
        Surface(modifier = Modifier.padding(contentPaddings)) {
            AppNavigation()
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun Preview_MainScreen() {
    DailyRoutineTheme {
        MainScreen()
    }
}