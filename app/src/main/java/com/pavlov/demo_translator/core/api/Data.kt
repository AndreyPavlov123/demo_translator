package com.pavlov.demo_translator.core.api

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

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

@Parcelize
data class MeaningShortRoot (
    var id: Int? = null,
    var text: String? = null,
    var meanings: List<MeaningShort>? = null
) : Parcelable

@Parcelize
data class Translation (
    var text: String? = null,
    var note: String? = null
) : Parcelable

data class Image (
    var url: String? = null
)

data class Definition (
    var text: String? = null,
    var soundUrl: String? = null
)

data class MeaningsWithSimilarTranslation (
    var meaningId: Int? = null,
    var frequencyPercent: String? = null,
    var partOfSpeechAbbreviation: String? = null,
    var translation: Translation? = null
)

data class AlternativeTranslation (
    var text: String? = null,
    var translation: Translation? = null
)

data class MeaningFull (
    var id: String? = null,
    var wordId: Int? = null,
    var difficultyLevel: Int? = null,
    var partOfSpeechCode: String? = null,
    var prefix: String? = null,
    var text: String? = null,
    var soundUrl: String? = null,
    var transcription: String? = null,
    var properties: Map<String, Any?>? = null,
    var updatedAt: String? = null,
    var mnemonics: String? = null,
    var translation: Translation? = null,
    var images: List<Image>? = null,
    var definition: Definition? = null,
    var examples: List<Definition>? = null,
    var meaningsWithSimilarTranslation: List<MeaningsWithSimilarTranslation>? = null,
    var alternativeTranslations: List<AlternativeTranslation>? = null
)