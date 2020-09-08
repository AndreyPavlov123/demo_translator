package com.pavlov.demo_translator.core.api.data

data class Word (
    var id: NumericId,
    var text: String? = null,
    var meanings: List<MeaningShort>? = null,
    var isViewExpanded: Boolean = false
)

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

data class Translation (
    var text: String? = null,
    var note: String? = null
)