package com.pavlov.demo_translator.core.api.data

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

val MeaningFull.correctSoundUrl get() = this.soundUrl?.correctUrl()
val MeaningFull.partOfSpeech get() = this.partOfSpeechCode?.decodePartOfSpeech()

data class Image (
    var url: String? = null
)

val Image.correctUrl get() = this.url?.correctUrl()

data class Definition (
    var text: String? = null,
    var soundUrl: String? = null
)

val Definition.correctSoundUrl get() = this.soundUrl?.correctUrl()

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
