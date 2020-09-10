package com.pavlov.demo_translator.meaning_detailed.repository.dto

import com.pavlov.demo_translator.common.NumericId
import com.pavlov.demo_translator.common.correctUrl
import com.pavlov.demo_translator.common.decodePartOfSpeech

data class MeaningFull(
    val id: String,
    val wordId: NumericId,
    val difficultyLevel: Int? = null,
    val partOfSpeechCode: String? = null,
    val prefix: String? = null,
    val text: String? = null,
    val soundUrl: String? = null,
    val transcription: String? = null,
    val properties: Map<String, Any?>? = null,
    val updatedAt: String? = null,
    val mnemonics: String? = null,
    val translation: Translation? = null,
    val images: List<Image>? = null,
    val definition: Definition? = null,
    val examples: List<Definition>? = null,
    val meaningsWithSimilarTranslation: List<MeaningsWithSimilarTranslation>? = null,
    val alternativeTranslations: List<AlternativeTranslation>? = null
)

val MeaningFull.correctSoundUrl get() = this.soundUrl?.correctUrl()
val MeaningFull.partOfSpeech get() = this.partOfSpeechCode?.decodePartOfSpeech()


