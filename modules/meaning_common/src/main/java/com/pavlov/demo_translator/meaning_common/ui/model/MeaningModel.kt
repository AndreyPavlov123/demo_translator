package com.pavlov.demo_translator.meaning_common.ui.model

import android.os.Parcelable
import com.pavlov.demo_translator.common.Navigator
import com.pavlov.demo_translator.common.NumericId
import kotlinx.android.parcel.Parcelize

typealias MeaningClickListener = (selectedMeaning: MeaningModel) -> Unit

@Parcelize
data class MeaningModel(
    val id: NumericId,
    val word: String,
    val translation: String,
    val previewUrl: String? = null,
    val partOfSpeech: String? = null,
    val imageUrl: String? = null,
    val transcription: String? = null,
    val soundUrl: String? = null
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
