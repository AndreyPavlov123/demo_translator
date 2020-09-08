package com.pavlov.demo_translator.ui.meaning

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pavlov.demo_translator.core.api.Api
import com.pavlov.demo_translator.core.api.data.MeaningShort
import com.pavlov.demo_translator.core.api.data.Word
import com.pavlov.demo_translator.core.api.data.correctImageUrl
import com.pavlov.demo_translator.core.api.data.correctSoundUrl
import com.pavlov.demo_translator.core.service.SoundService
import com.pavlov.demo_translator.ui.tools.SingleLiveEvent

class MeaningViewModel @ViewModelInject constructor(
    private val soundService: SoundService,
    private val api: Api
) : ViewModel() {

    private lateinit var word: Word
    private lateinit var meaningShort: MeaningShort
    private var selectedMeaning: Int = -1

    fun init(word: Word, selectedMeaning: Int) {
        this.word = word
        meaningShort = word.meanings!![selectedMeaning]
        this.selectedMeaning = selectedMeaning
        title.value = word.text
        image.value = meaningShort.correctImageUrl
    }

    val title: MutableLiveData<String> = MutableLiveData()
    val image: MutableLiveData<String> = MutableLiveData()
    val closeEvent : SingleLiveEvent<Any?> = SingleLiveEvent()

    fun playButtonClicked() = meaningShort.correctSoundUrl?.apply { soundService.playSound(this) }
    fun closeButtonClicked() = closeEvent.call()
}