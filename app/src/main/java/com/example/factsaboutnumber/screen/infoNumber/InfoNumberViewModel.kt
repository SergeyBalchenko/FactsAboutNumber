package com.example.factsaboutnumber.screen.infoNumber

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class InfoNumberViewModel: ViewModel() {
}

class InfoNumberViewModelFactory @Inject constructor(

): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return InfoNumberViewModel(

        ) as T
    }
}