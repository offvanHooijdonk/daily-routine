package by.offvanhooijdonk.dailyroutine.di

import by.offvanhooijdonk.dailyroutine.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val uiModule = module {
    viewModelOf(::MainViewModel)
}