package com.pavlov.demo_translator.meaning_detailed.repository.dto

import com.pavlov.demo_translator.common.NumericId

data class MeaningsWithSimilarTranslation (
    var meaningId: NumericId,
    var frequencyPercent: String? = null,
    var partOfSpeechAbbreviation: String? = null,
    var translation: Translation? = null
)