package com.pavlov.demo_translator.meaning_detailed.repository.dto

import com.pavlov.demo_translator.common.correctUrl

data class Image (
    val url: String? = null
)

val Image.correctUrl get() = this.url?.correctUrl()