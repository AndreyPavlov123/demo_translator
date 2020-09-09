package com.pavlov.demo_translator.meaning_common.ui.model

import android.os.Parcelable
import com.pavlov.demo_translator.common.Navigator
import com.pavlov.demo_translator.common.NumericId
import kotlinx.android.parcel.Parcelize

typealias MeaningClickListener = (selectedMeaning: MeaningModel) -> Unit

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

fun MeaningModel.toMeaningScreenArgs() = Navigator.MeaningScreen.Args(
    id,
    word,
    translation,
    previewUrl,
    partOfSpeech,
    imageUrl,
    transcription,
    soundUrl,
)
