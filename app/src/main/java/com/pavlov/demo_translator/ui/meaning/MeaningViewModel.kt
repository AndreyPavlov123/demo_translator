package com.pavlov.demo_translator.ui.meaning

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.pavlov.demo_translator.core.api.Api
import com.pavlov.demo_translator.core.api.data.MeaningFull
import com.pavlov.demo_translator.core.api.data.NumericId
import com.pavlov.demo_translator.core.api.data.correctSoundUrl
import com.pavlov.demo_translator.core.api.data.correctUrl
import com.pavlov.demo_translator.core.service.SoundService
import com.pavlov.demo_translator.ui.model.MeaningModel
import com.pavlov.demo_translator.ui.model.SelectedMeaningModel
import com.pavlov.demo_translator.ui.model.toModel
import com.pavlov.demo_translator.ui.tools.SingleLiveEvent
import kotlinx.coroutines.launch

class MeaningViewModel @ViewModelInject constructor(
    private val soundService: SoundService,
    private val api: Api,
    @Assisted savedStateHandle: SavedStateHandle
) : ViewModel() {

    private var selectedMeaning = savedStateHandle.get<SelectedMeaningModel>("selectedMeaning")!!
    private var meaning: MeaningFull? = null

    val title = MutableLiveData<String>()
    val image = MutableLiveData<String>()
    val otherMeanings = MutableLiveData<List<MeaningModel>>()
    val closeEvent = SingleLiveEvent<Any?>()
    val snackbarEvent = SingleLiveEvent<String>()
    val openMeaningScreenEvent = SingleLiveEvent<SelectedMeaningModel>()
    val loading = MutableLiveData<Loading>()

    init {
        selectedMeaning.initialMeaningData?.let {
            title.value = it.word
            image.value = it.imageUrl
        }
        load()
    }

    private fun load() {
        viewModelScope.launch {
            try {
                if (selectedMeaning.initialMeaningData == null) {
                    loading.value = Loading(true, "Loading...", null)
                }

                val response = api.meanings(selectedMeaning.selectedMeaningId)

                loading.value = Loading(false, null, null)

                val meaningFull = response[0]
                meaning = meaningFull
                if (!meaningFull.prefix.isNullOrBlank()) {
                    title.value = "${meaningFull.prefix} ${meaningFull.text}"
                }
                else {
                    title.value = meaningFull.text
                }
                if (image.value.isNullOrBlank()) {
                    meaningFull.images?.firstOrNull()?.let { image.value = it.correctUrl }
                }
                meaningFull.meaningsWithSimilarTranslation?.let {
                    otherMeanings.value = it.filter { t -> t.meaningId != selectedMeaning.selectedMeaningId }.map { t -> t.toModel(meaningFull.text ?: "") }
                }
            } catch (exception: Exception) {
                loading.value = Loading(false, "Error while loading a detailed info of the meaning", "Retry")
            }
        }
    }

    fun playButtonClicked() = meaning?.correctSoundUrl?.apply { soundService.playSound(this) }
    fun closeButtonClicked() = closeEvent.call()
    fun otherMeaningClicked(selectedMeaning: SelectedMeaningModel) {
        openMeaningScreenEvent.value = selectedMeaning.copy(initialMeaningData = null)
    }
    fun retryButtonClicked() = load()

    data class Loading(val isLoading: Boolean, val message: String?, val retryButton: String?) {
        val isMessageVisible = !message.isNullOrBlank()
        val isRetryButtonVisible = !retryButton.isNullOrBlank()
        val isLoadingLayoutVisible = isLoading || isMessageVisible || isRetryButtonVisible
    }
}