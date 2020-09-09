package com.pavlov.demo_translator.meaning_common.ui.model

import android.os.Parcelable
import com.pavlov.demo_translator.common.NumericId
import kotlinx.android.parcel.Parcelize

typealias MeaningClickListener = (selectedMeaning: SelectedMeaningModel) -> Unit

@Parcelize
data class SelectedMeaningModel(
    val selectedMeaningId: NumericId,
    val initialMeaningData: MeaningModel?
) : Parcelable