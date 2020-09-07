package com.pavlov.demo_translator.ui.meaning

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pavlov.demo_translator.core.api.Api
import kotlinx.coroutines.launch

class MeaningViewModel @ViewModelInject constructor(api: Api) : ViewModel() {
    init {
        viewModelScope.launch {
            val r = api.meanings(26)
            p.value = r.body()?.first()?.text
        }

    }

    val p: MutableLiveData<String> = MutableLiveData("default")
}