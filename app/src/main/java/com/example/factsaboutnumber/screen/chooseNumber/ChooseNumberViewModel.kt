package com.example.factsaboutnumber.screen.chooseNumber

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.factsaboutnumber.db.entities.NumberInfo
import com.example.factsaboutnumber.usecase.SearchNumberInfoUseCase
import com.example.factsaboutnumber.usecase.SubscribeToNumberInfoUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

class ChooseNumberViewModel(
    private val searchNumberInfoUseCase: SearchNumberInfoUseCase,
    private val subscribeToNumberInfoUseCase: SubscribeToNumberInfoUseCase
) : ViewModel() {

    private val _viewState = MutableStateFlow<List<NumberInfo>>(emptyList())
    val viewState = _viewState.asStateFlow()

    private val _effects = MutableSharedFlow<Effect>()
    val effects = _effects.asSharedFlow()

    init {
        viewModelScope.launch {
            subscribeToNumberInfoUseCase.invoke().stateIn(this).collect {
                _viewState.emit(it)
            }
        }
    }

    fun search(number: Int? = null) {
        viewModelScope.launch {
            searchNumberInfoUseCase.invoke(number).fold(
                onSuccess = {
                    // no-op
                },
                onFailure = {
                    _effects.emit(Effect.NotFound)
                }
            )
        }
    }
}

sealed class Effect {
    object NotFound : Effect()
}

class ChooseNumberViewModelFactory @Inject constructor(
    private val searchNumberInfoUseCase: SearchNumberInfoUseCase,
    private val subscribeToNumberInfoUseCase: SubscribeToNumberInfoUseCase
): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ChooseNumberViewModel(
            searchNumberInfoUseCase,
            subscribeToNumberInfoUseCase
        ) as T
    }
}