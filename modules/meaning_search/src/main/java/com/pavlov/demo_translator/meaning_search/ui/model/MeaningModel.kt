package com.pavlov.demo_translator.meaning_search.ui.model

import com.pavlov.demo_translator.meaning_common.ui.model.MeaningModel
import com.pavlov.demo_translator.meaning_search.repository.dto.*

fun MeaningShort.toModel(word: String) = MeaningModel(
    this.id,
    word,
    this.translation?.text ?: "",
    this.correctPreviewUrl,
    partOfSpeech = this.partOfSpeech,
    imageUrl = this.correctImageUrl,
    transcription = this.transcription,
    soundUrl = this.correctSoundUrl,
)
