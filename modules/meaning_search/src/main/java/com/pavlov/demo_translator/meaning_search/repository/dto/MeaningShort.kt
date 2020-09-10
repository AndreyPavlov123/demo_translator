package com.pavlov.demo_translator.meaning_search.repository.dto

import com.pavlov.demo_translator.common.NumericId
import com.pavlov.demo_translator.common.correctUrl
import com.pavlov.demo_translator.common.decodePartOfSpeech

data class MeaningShort(
    val id: NumericId,
    val partOfSpeechCode: String? = null,
    val translation: Translation? = null,
    val previewUrl: String? = null,
    val imageUrl: String? = null,
    val transcription: String? = null,
    val soundUrl: String? = null
)

val MeaningShort.correctPreviewUrl get() = this.previewUrl?.correctUrl()
val MeaningShort.correctImageUrl get() = this.imageUrl?.correctUrl()
val MeaningShort.correctSoundUrl get() = this.soundUrl?.correctUrl()
val MeaningShort.partOfSpeech get() = this.partOfSpeechCode?.decodePartOfSpeech()