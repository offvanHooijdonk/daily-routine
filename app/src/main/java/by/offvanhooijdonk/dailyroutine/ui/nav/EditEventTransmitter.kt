package by.offvanhooijdonk.dailyroutine.ui.nav

import by.offvanhooijdonk.dailyroutine.ui.main.MainViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class EditEventTransmitter {
    private val _transmitter = MutableSharedFlow<MainViewModel.EditEvent>()
    val events = _transmitter.asSharedFlow()

    suspend fun sentEditEvent(event: MainViewModel.EditEvent) {
        _transmitter.emit(event)
    }
}