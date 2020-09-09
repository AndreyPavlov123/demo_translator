package com.pavlov.demo_translator.meaning_search.repository.dto

import com.pavlov.demo_translator.common.NumericId
import com.pavlov.demo_translator.common.correctUrl
import com.pavlov.demo_translator.common.decodePartOfSpeech

data class MeaningShort (
    var id: NumericId,
    var partOfSpeechCode: String? = null,
    var translation: Translation? = null,
    var previewUrl: String? = null,
    var imageUrl: String? = null,
    var transcription: String? = null,
    var soundUrl: String? = null
)

val MeaningShort.correctPreviewUrl get() = this.previewUrl?.correctUrl()
val MeaningShort.correctImageUrl get() = this.imageUrl?.correctUrl()
val MeaningShort.correctSoundUrl get() = this.soundUrl?.correctUrl()
val MeaningShort.partOfSpeech get() = this.partOfSpeechCode?.decodePartOfSpeech()