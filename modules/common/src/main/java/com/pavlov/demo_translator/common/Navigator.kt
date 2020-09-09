package com.pavlov.demo_translator.common

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

interface Navigator {
    interface MeaningScreen {
        @Parcelize
        data class Args(
            var id: NumericId,
            var word: String,
            var translation: String,
            var previewUrl: String? = null,
            var partOfSpeech: String? = null,
            var imageUrl: String? = null,
            var transcription: String? = null,
            var soundUrl: String? = null
        ) : Parcelable

        fun navigate(args: Args)
    }
}