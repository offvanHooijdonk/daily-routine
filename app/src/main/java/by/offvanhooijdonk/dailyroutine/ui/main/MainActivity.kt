package by.offvanhooijdonk.dailyroutine.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import by.offvanhooijdonk.dailyroutine.ui.nav.NavHolder
import by.offvanhooijdonk.dailyroutine.ui.theme.DailyRoutineTheme
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    private val viewModel by viewModel<MainViewModel>()
    private val navHolder by inject<NavHolder>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DailyRoutineTheme {
                MainScreen(viewModel, navHolder)
            }
        }
    }
}