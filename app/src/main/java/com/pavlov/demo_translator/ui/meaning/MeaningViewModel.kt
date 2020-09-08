package com.pavlov.demo_translator.ui.meaning

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.pavlov.demo_translator.core.api.Api
import com.pavlov.demo_translator.core.api.data.correctImageUrl
import com.pavlov.demo_translator.core.api.data.correctSoundUrl
import com.pavlov.demo_translator.core.service.SoundService
import com.pavlov.demo_translator.ui.search.adapter.SelectedMeaning
import com.pavlov.demo_translator.ui.tools.SingleLiveEvent
import kotlinx.coroutines.launch

class MeaningViewModel @ViewModelInject constructor(
    private val soundService: SoundService,
    private val api: Api,
    @Assisted savedStateHandle: SavedStateHandle
) : ViewModel() {

    private var selectedMeaning: SelectedMeaning = savedStateHandle.get<SelectedMeaning>("selectedMeaning")!!

    val title = MutableLiveData(selectedMeaning.word.text)
    val image = MutableLiveData(selectedMeaning.meaning.correctImageUrl)
    val closeEvent = SingleLiveEvent<Any?>()
    val snackbarEvent = SingleLiveEvent<String>()

    init {
        viewModelScope.launch {
            try {
                val response = api.meanings(selectedMeaning.meaning.id!!)
                val meaningFull = response[0]
                if (!meaningFull.prefix.isNullOrBlank()) {
                    title.value = "${meaningFull.prefix} ${selectedMeaning.word.text}"
                }
            } catch (exception: Exception) {
                snackbarEvent.value = "Error while loading a detailed info of the meaning"
            }
        }
    }

    fun playButtonClicked() = selectedMeaning.meaning.correctSoundUrl?.apply { soundService.playSound(this) }
    fun closeButtonClicked() = closeEvent.call()
}