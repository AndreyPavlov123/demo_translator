package com.pavlov.demo_translator.meaning_search.repository.dto

import com.pavlov.demo_translator.common.NumericId

data class Word (
    var id: NumericId,
    var text: String? = null,
    var meanings: List<MeaningShort>? = null,
    var isViewExpanded: Boolean = false
)