package com.pavlov.demo_translator.ui.meaning

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pavlov.demo_translator.core.api.Api
import com.pavlov.demo_translator.core.api.data.correctImageUrl
import com.pavlov.demo_translator.core.api.data.correctSoundUrl
import com.pavlov.demo_translator.core.service.SoundService
import com.pavlov.demo_translator.ui.search.adapter.SelectedMeaning
import com.pavlov.demo_translator.ui.tools.SingleLiveEvent
import kotlinx.coroutines.launch

class MeaningViewModel @ViewModelInject constructor(
    private val soundService: SoundService,
    private val api: Api
) : ViewModel() {

    private lateinit var selectedMeaning: SelectedMeaning

    fun init(selectedMeaning: SelectedMeaning) {
        this.selectedMeaning = selectedMeaning
        title.value = selectedMeaning.word.text
        image.value = selectedMeaning.meaning.correctImageUrl

        viewModelScope.launch {
        }
    }

    val title: MutableLiveData<String> = MutableLiveData()
    val image: MutableLiveData<String> = MutableLiveData()
    val closeEvent : SingleLiveEvent<Any?> = SingleLiveEvent()

    fun playButtonClicked() = selectedMeaning.meaning.correctSoundUrl?.apply { soundService.playSound(this) }
    fun closeButtonClicked() = closeEvent.call()
}