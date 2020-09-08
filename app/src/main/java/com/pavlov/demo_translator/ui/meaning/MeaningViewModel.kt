package com.pavlov.demo_translator.ui.meaning

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pavlov.demo_translator.core.api.MeaningShort
import com.pavlov.demo_translator.core.api.MeaningShortRoot
import com.pavlov.demo_translator.core.service.SoundService
import com.pavlov.demo_translator.ui.tools.SingleLiveEvent

class MeaningViewModel @ViewModelInject constructor(private val soundService: SoundService) : ViewModel() {

    private lateinit var meaningShortRoot: MeaningShortRoot
    private lateinit var meaningShort: MeaningShort
    private var selectedMeaning: Int = -1

    fun init(meaningShortRoot: MeaningShortRoot, selectedMeaning: Int) {
        this.meaningShortRoot = meaningShortRoot
        meaningShort = meaningShortRoot.meanings!![selectedMeaning]
        this.selectedMeaning = selectedMeaning
        title.value = meaningShortRoot.text
        image.value = "https:" + meaningShort.imageUrl
    }

    val title: MutableLiveData<String> = MutableLiveData()
    val image: MutableLiveData<String> = MutableLiveData()
    val closeEvent : SingleLiveEvent<Any?> = SingleLiveEvent()

    fun playButtonClicked() = soundService.playSound("https:" + meaningShort.soundUrl)
    fun closeButtonClicked() = closeEvent.call()
}