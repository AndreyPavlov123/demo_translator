package com.pavlov.demo_translator

import androidx.fragment.app.Fragment
import com.pavlov.demo_translator.common.Navigator
import com.pavlov.demo_translator.meaning_detailed.ui.MeaningFragment
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import javax.inject.Inject

class MeaningScreenNavigatorImpl @Inject constructor(
    private val fragment: Fragment
) : Navigator.MeaningScreen {

    override fun navigate(args: Navigator.MeaningScreen.Args) = MeaningFragment.newInstance(args)
        .show(fragment.childFragmentManager, "MeaningScreen${args.id}")
}

@Module
@InstallIn(FragmentComponent::class)
abstract class FragmentNavigatorModule {

    @Binds
    abstract fun bindNavigator(navigatorImpl: MeaningScreenNavigatorImpl): Navigator.MeaningScreen
}