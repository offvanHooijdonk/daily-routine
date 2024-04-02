@file:OptIn(ExperimentalMaterial3Api::class)

package by.offvanhooijdonk.dailyroutine.ui.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import by.offvanhooijdonk.dailyroutine.R

@Composable
fun TopBar() {
    MediumTopAppBar(title = {
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            Text(text = stringResource(R.string.app_bar_title))
        }
    })
}