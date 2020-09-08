package com.pavlov.demo_translator.core.api.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Word (
    var id: Int? = null,
    var text: String? = null,
    var meanings: List<MeaningShort>? = null,
    var isViewExpanded: Boolean = false
) : Parcelable

@Parcelize
data class MeaningShort (
    var id: Int? = null,
    var partOfSpeechCode: String? = null,
    var translation: Translation? = null,
    var previewUrl: String? = null,
    var imageUrl: String? = null,
    var transcription: String? = null,
    var soundUrl: String? = null
) : Parcelable

val MeaningShort.correctPreviewUrl get() = this.previewUrl?.correctUrl()
val MeaningShort.correctImageUrl get() = this.imageUrl?.correctUrl()
val MeaningShort.correctSoundUrl get() = this.soundUrl?.correctUrl()

@Parcelize
data class Translation (
    var text: String? = null,
    var note: String? = null
) : Parcelable