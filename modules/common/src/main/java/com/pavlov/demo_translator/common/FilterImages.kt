package com.pavlov.demo_translator.common

fun Int.getFilterImageId() = when (this) {
    1 -> R.drawable.ic_filter_1
    2 -> R.drawable.ic_filter_2
    3 -> R.drawable.ic_filter_3
    4 -> R.drawable.ic_filter_4
    5 -> R.drawable.ic_filter_5
    6 -> R.drawable.ic_filter_6
    7 -> R.drawable.ic_filter_7
    8 -> R.drawable.ic_filter_8
    9 -> R.drawable.ic_filter_9
    else -> R.drawable.ic_filter_9_plus
}