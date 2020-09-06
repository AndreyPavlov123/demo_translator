package com.pavlov.demo_translator.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pavlov.demo_translator.api.Api
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(api: Api) : ViewModel() {
    init {
        viewModelScope.launch {
            val r = api.search("работа")
            p.value = r.body()?.first()?.text
        }

    }

    val p: MutableLiveData<String> = MutableLiveData("default")
}