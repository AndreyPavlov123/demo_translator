package com.pavlov.demo_translator.ui

import android.os.Bundle
import androidx.fragment.app.FragmentManager
import com.pavlov.demo_translator.common.Navigator
import com.pavlov.demo_translator.meaning_detailed.ui.MeaningFragment
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Inject

class NavigatorImpl @Inject constructor() : Navigator {
    override fun showMeaningScreen(
        fragmentManager: FragmentManager,
        arguments: Bundle,
        tag: String
    ) {
        MeaningFragment().apply { setArguments(arguments) }.show(fragmentManager, tag)
    }
}

@Module
@InstallIn(ActivityComponent::class)
abstract class NavigatorModule {

    @Binds
    abstract fun bindNavigator(navigatorImpl: NavigatorImpl): Navigator
}