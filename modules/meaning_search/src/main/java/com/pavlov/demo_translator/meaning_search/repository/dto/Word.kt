package com.pavlov.demo_translator.meaning_search.repository.dto

import com.pavlov.demo_translator.common.NumericId

data class Word (
    val id: NumericId,
    val text: String? = null,
    val meanings: List<MeaningShort>? = null,
    val isViewExpanded: Boolean = false
)