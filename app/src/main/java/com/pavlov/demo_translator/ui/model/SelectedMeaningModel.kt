package com.pavlov.demo_translator.ui.model

import android.os.Parcelable
import com.pavlov.demo_translator.core.api.data.NumericId
import kotlinx.android.parcel.Parcelize

typealias MeaningClickListener = (selectedMeaning: SelectedMeaningModel) -> Unit

@Parcelize
data class SelectedMeaningModel(
    val selectedMeaningId: NumericId,
    val initialMeaningData: MeaningModel?
) : Parcelable