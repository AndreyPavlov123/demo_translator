package com.pavlov.demo_translator.meaning_detailed.repository.dto

import com.pavlov.demo_translator.common.correctUrl

data class Definition(
    val text: String? = null,
    val soundUrl: String? = null
)

val Definition.correctSoundUrl get() = this.soundUrl?.correctUrl()