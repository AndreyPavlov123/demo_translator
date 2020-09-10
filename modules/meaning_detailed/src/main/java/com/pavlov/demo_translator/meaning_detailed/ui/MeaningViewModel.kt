package com.pavlov.demo_translator.meaning_detailed.ui

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.pavlov.demo_translator.common.Navigator
import com.pavlov.demo_translator.common.SingleLiveEvent
import com.pavlov.demo_translator.common.SoundInteractor
import com.pavlov.demo_translator.meaning_common.ui.model.MeaningModel
import com.pavlov.demo_translator.meaning_common.ui.model.toMeaningScreenArgs
import com.pavlov.demo_translator.meaning_detailed.repository.MeaningApi
import com.pavlov.demo_translator.meaning_detailed.repository.dto.Definition
import com.pavlov.demo_translator.meaning_detailed.repository.dto.MeaningFull
import com.pavlov.demo_translator.meaning_detailed.repository.dto.correctSoundUrl
import com.pavlov.demo_translator.meaning_detailed.repository.dto.correctUrl
import com.pavlov.demo_translator.meaning_detailed.ui.model.toModel
import kotlinx.coroutines.launch

class MeaningViewModel @ViewModelInject constructor(
    private val soundInteractor: SoundInteractor,
    private val api: MeaningApi,
    @Assisted savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val selectedMeaning =
        savedStateHandle.get<Navigator.MeaningScreen.Args>(SELECTED_MEANING_TAG)!!
    private val isPreloaded = !selectedMeaning.imageUrl.isNullOrBlank() &&
            !selectedMeaning.soundUrl.isNullOrBlank() &&
            !selectedMeaning.transcription.isNullOrBlank()
    private var soundUrl = selectedMeaning.soundUrl
    private var meaning: MeaningFull? = null

    val title = MutableLiveData(selectedMeaning.word)
    val word by ::title
    val image = MutableLiveData(selectedMeaning.imageUrl)
    val translation = MutableLiveData(selectedMeaning.translation)
    val transcription = MutableLiveData(selectedMeaning.transcription)
    val definition = MutableLiveData<String>()
    val definitionVisible = definition.map { !it.isNullOrEmpty() }
    val otherMeanings = MutableLiveData<List<MeaningModel>>(emptyList())
    val otherMeaningTitleVisible = otherMeanings.map { it.isNotEmpty() }
    val examples = MutableLiveData<List<Definition>>(emptyList())
    val closeEvent = SingleLiveEvent<Any?>()
    val snackbarEvent = SingleLiveEvent<String>()
    val loading = MutableLiveData<Loading>()
    val navigateToMeaningScreen = SingleLiveEvent<Navigator.MeaningScreen.Args>()

    init {
        load()
    }

    private fun load() {
        viewModelScope.launch {
            try {
                if (!isPreloaded) {
                    loading.value = Loading(true, "Loading...", null)
                }

                val response = api.meanings(selectedMeaning.id)

                loading.value = Loading(false, null, null)

                val meaningFull = response[0]
                meaning = meaningFull
                if (!meaningFull.prefix.isNullOrBlank()) {
                    title.value = "${meaningFull.prefix} ${meaningFull.text}"
                } else {
                    title.value = meaningFull.text
                }
                if (image.value.isNullOrBlank()) {
                    meaningFull.images?.firstOrNull()?.let { image.value = it.correctUrl }
                }

                transcription.value = "/ ${meaningFull.transcription} /"
                translation.value = meaningFull.translation?.text
                definition.value = meaningFull.definition?.text
                soundUrl = meaningFull.correctSoundUrl

                meaningFull.meaningsWithSimilarTranslation?.let {
                    otherMeanings.value = it.filter { t -> t.meaningId != selectedMeaning.id }
                        .map { t -> t.toModel(meaningFull.text ?: "") }
                }
                meaningFull.examples?.let { examples.value = it }
            } catch (exception: Exception) {
                loading.value =
                    Loading(false, "Error while loading a detailed info of the meaning", "Retry")
            }
        }
    }

    fun playButtonClicked() = soundUrl?.apply { soundInteractor.playSound(this) }
    fun closeButtonClicked() = closeEvent.call()
    fun otherMeaningClicked(selectedMeaning: MeaningModel) {
        navigateToMeaningScreen.value = selectedMeaning.toMeaningScreenArgs()
    }

    fun retryButtonClicked() = load()
    fun playExampleClicked(soundUrl: String) = soundInteractor.playSound(soundUrl)

    data class Loading(val isLoading: Boolean, val message: String?, val retryButton: String?) {
        val isMessageVisible = !message.isNullOrBlank()
        val isRetryButtonVisible = !retryButton.isNullOrBlank()
        val isLoadingLayoutVisible = isLoading || isMessageVisible || isRetryButtonVisible
    }

    companion object {
        const val SELECTED_MEANING_TAG = "selectedMeaning"
    }
}