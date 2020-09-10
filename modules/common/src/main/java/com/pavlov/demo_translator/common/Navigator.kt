package com.pavlov.demo_translator.common

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

interface Navigator {
    interface MeaningScreen {
        @Parcelize
        data class Args(
            val id: NumericId,
            val word: String,
            val translation: String,
            val previewUrl: String? = null,
            val partOfSpeech: String? = null,
            val imageUrl: String? = null,
            val transcription: String? = null,
            val soundUrl: String? = null
        ) : Parcelable

        fun navigate(args: Args)
    }
}