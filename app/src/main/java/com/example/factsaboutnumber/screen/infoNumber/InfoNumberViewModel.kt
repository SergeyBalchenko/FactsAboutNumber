package com.example.factsaboutnumber.screen.infoNumber

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.factsaboutnumber.db.entities.NumberInfo
import com.example.factsaboutnumber.usecase.GetNumberDetailsUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class InfoNumberViewModel(
    private val getNumberDetailsUseCase: GetNumberDetailsUseCase
) : ViewModel() {

    private val _viewState = MutableStateFlow<NumberInfo?>(null)
    val viewState = _viewState.asStateFlow()

    private val _effects = MutableSharedFlow<Effect>()
    val effects = _effects.asSharedFlow()
    fun getNumberDetails(number: Int) {
        viewModelScope.launch {
            getNumberDetailsUseCase.invoke(number).fold(
                onSuccess = {
                    _viewState.emit(it)
                },
                onFailure = {
                    _effects.emit(Effect.CantFoundNumberInfo(it.message))
                }
            )
        }
    }
}

sealed class Effect {
    data class CantFoundNumberInfo(val e: String?): Effect()
}

class InfoNumberViewModelFactory @Inject constructor(
    private val getNumberDetailsUseCase: GetNumberDetailsUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return InfoNumberViewModel(
            getNumberDetailsUseCase
        ) as T
    }
}