package com.pavlov.demo_translator.meaning_detailed.ui.model

import com.pavlov.demo_translator.meaning_common.ui.model.MeaningModel
import com.pavlov.demo_translator.meaning_detailed.repository.dto.MeaningsWithSimilarTranslation

fun MeaningsWithSimilarTranslation.toModel(word: String) = MeaningModel(
    this.meaningId,
    word,
    this.translation?.text ?: "",
)
