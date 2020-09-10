package com.pavlov.demo_translator.meaning_detailed.repository.dto

import com.pavlov.demo_translator.common.NumericId

data class MeaningsWithSimilarTranslation(
    val meaningId: NumericId,
    val frequencyPercent: String? = null,
    val partOfSpeechAbbreviation: String? = null,
    val translation: Translation? = null
)