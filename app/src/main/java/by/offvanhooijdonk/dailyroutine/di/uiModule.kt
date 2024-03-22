package by.offvanhooijdonk.dailyroutine.di

import by.offvanhooijdonk.dailyroutine.ui.main.MainViewModel
import by.offvanhooijdonk.dailyroutine.ui.timeline.TimelineListViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val uiModule = module {
    viewModelOf(::MainViewModel)
    viewModelOf(::TimelineListViewModel)
}