package com.pavlov.demo_translator.ui.model

import android.os.Parcelable
import com.pavlov.demo_translator.core.api.data.*
import kotlinx.android.parcel.Parcelize

typealias MeaningClickListener = (selectedMeaning: SelectedMeaningModel) -> Unit

@Parcelize
data class SelectedMeaningModel(
    val selectedMeaningId: NumericId,
    val initialMeaningData: MeaningModel?
) : Parcelable

@Parcelize
data class MeaningModel (
    var id: NumericId,
    var word: String,
    var translation: String,
    var previewUrl: String? = null,
    var partOfSpeech: String? = null,
    var imageUrl: String? = null,
    var transcription: String? = null,
    var soundUrl: String? = null
) : Parcelable

fun MeaningShort.toModel(word: String) = MeaningModel(
    this.id,
    word,
    this.translation?.text ?: "",
    this.correctPreviewUrl,
    partOfSpeech = this.partOfSpeech,
    imageUrl = this.correctImageUrl,
    transcription = this.transcription,
    soundUrl = this.correctSoundUrl,
)

fun MeaningsWithSimilarTranslation.toModel(word: String) = MeaningModel(
    this.meaningId,
    word,
    this.translation?.text ?: ""
)