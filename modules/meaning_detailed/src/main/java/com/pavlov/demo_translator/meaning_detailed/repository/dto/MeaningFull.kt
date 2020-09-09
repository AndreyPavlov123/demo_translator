package com.pavlov.demo_translator.meaning_detailed.repository.dto

import com.pavlov.demo_translator.common.NumericId
import com.pavlov.demo_translator.common.correctUrl
import com.pavlov.demo_translator.common.decodePartOfSpeech

data class MeaningFull (
    var id: String,
    var wordId: NumericId,
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


