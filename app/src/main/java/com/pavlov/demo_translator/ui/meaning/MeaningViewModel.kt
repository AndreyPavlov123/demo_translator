package com.pavlov.demo_translator.ui.meaning

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pavlov.demo_translator.core.api.Api
import com.pavlov.demo_translator.core.api.MeaningShortRoot
import kotlinx.coroutines.launch

class MeaningViewModel @ViewModelInject constructor(api: Api) : ViewModel() {

    fun setData(data: MeaningShortRoot) {
        title.value = data.text
    }

    val title: MutableLiveData<String> = MutableLiveData()
}