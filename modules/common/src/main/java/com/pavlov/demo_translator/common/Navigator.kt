package com.pavlov.demo_translator.common

import android.os.Bundle
import androidx.fragment.app.FragmentManager

interface Navigator {
    fun showMeaningScreen(fragmentManager: FragmentManager, arguments: Bundle, tag: String)
}