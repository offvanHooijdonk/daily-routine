package by.offvanhooijdonk.dailyroutine.di

import by.offvanhooijdonk.dailyroutine.ui.main.AddTaskContainer
import by.offvanhooijdonk.dailyroutine.ui.nav.EditEventTransmitter
import by.offvanhooijdonk.dailyroutine.ui.main.MainViewModel
import by.offvanhooijdonk.dailyroutine.ui.nav.NavHolder
import by.offvanhooijdonk.dailyroutine.ui.more_screen.MoreScreenViewModel
import by.offvanhooijdonk.dailyroutine.ui.termless.TermlessListViewModel
import by.offvanhooijdonk.dailyroutine.ui.timeline.TimelineListViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.qualifier.qualifier
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import pro.respawn.flowmvi.android.StoreViewModel

val uiModule = module {
    viewModelOf(::MainViewModel)
    viewModelOf(::TimelineListViewModel)
    viewModelOf(::TermlessListViewModel)
    viewModelOf(::MoreScreenViewModel)

    single { NavHolder() }
    single { EditEventTransmitter() }
    viewModel(qualifier<AddTaskContainer>()) { StoreViewModel(get<AddTaskContainer>()) }
    single { AddTaskContainer(get()) }
}