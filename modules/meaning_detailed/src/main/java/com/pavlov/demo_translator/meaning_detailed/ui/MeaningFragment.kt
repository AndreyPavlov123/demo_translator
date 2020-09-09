package com.pavlov.demo_translator.meaning_detailed.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.pavlov.demo_translator.common.Navigator
import com.pavlov.demo_translator.meaning_common.ui.adapter.MeaningAdapter
import com.pavlov.demo_translator.meaning_detailed.R
import com.pavlov.demo_translator.meaning_detailed.databinding.FragmentMeaningBinding
import com.pavlov.demo_translator.meaning_detailed.ui.adapter.ExampleAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MeaningFragment : DialogFragment() {

    companion object {
        fun newInstance(selectedMeaning: Navigator.MeaningScreen.Args) = MeaningFragment().apply {
            arguments = Bundle().apply {
                putParcelable(MeaningViewModel.SELECTED_MEANING_TAG, selectedMeaning)
            }
        }
    }

    @Inject lateinit var meaningScreenNavigator: Navigator.MeaningScreen
    private val viewModel: MeaningViewModel by viewModels()
    private lateinit var binding: FragmentMeaningBinding
    private val otherMeaningsAdapter = MeaningAdapter {
        viewModel.otherMeaningClicked(it)
    }
    private val exampleAdapter = ExampleAdapter {
        viewModel.playExampleClicked(it)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.AppThemeDialogWhenLarge)
        setHasOptionsMenu(false)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMeaningBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.setNavigationOnClickListener {
            viewModel.closeButtonClicked()
        }
        binding.fab.setOnClickListener {
            viewModel.playButtonClicked()
        }
        binding.loadingLayout.loadingLayoutButton.setOnClickListener {
            viewModel.retryButtonClicked()
        }

        binding.scrollView.otherMeanings.adapter = otherMeaningsAdapter
        binding.scrollView.examples.adapter = exampleAdapter

        var isToolbarShown = false

        // scroll change listener begins at Y = 0 when image is fully collapsed
        binding.scrollView.scrollView.setOnScrollChangeListener(
            NestedScrollView.OnScrollChangeListener { _, _, scrollY, _, _ ->

                // User scrolled past image to height of toolbar and the title text is
                // underneath the toolbar, so the toolbar should be shown.
                val shouldShowToolbar = scrollY > binding.toolbar.height

                // The new state of the toolbar differs from the previous state; update
                // appbar and toolbar attributes.
                if (isToolbarShown != shouldShowToolbar) {
                    isToolbarShown = shouldShowToolbar

                    // Use shadow animator to add elevation if toolbar is shown
                    //appbar.isActivated = shouldShowToolbar

                    // Show the plant name if toolbar is shown
                    binding.toolbarLayout.isTitleEnabled = shouldShowToolbar
                }
            }
        )
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.title.observe(viewLifecycleOwner) {
            binding.toolbarLayout.title = it
        }
        viewModel.word.observe(viewLifecycleOwner) {
            binding.scrollView.word.text = it
        }
        viewModel.transcription.observe(viewLifecycleOwner) {
            binding.scrollView.transcription.text = it
        }
        viewModel.translation.observe(viewLifecycleOwner) {
            binding.scrollView.translation.text = it
        }
        viewModel.definition.observe(viewLifecycleOwner) {
            binding.scrollView.definition.text = it
        }
        viewModel.definitionVisible.observe(viewLifecycleOwner) {
            binding.scrollView.definition.isVisible = it
        }
        viewModel.image.observe(viewLifecycleOwner) {
            Glide.with(this).load(it).into(binding.detailImage)
        }
        viewModel.closeEvent.observe(viewLifecycleOwner) {
            dismiss()
        }
        viewModel.snackbarEvent.observe(viewLifecycleOwner) {
            Snackbar.make(binding.coordinatorLayout, it, Snackbar.LENGTH_LONG).show()
        }
        viewModel.otherMeanings.observe(viewLifecycleOwner){
            otherMeaningsAdapter.submitList(it)
        }
        viewModel.otherMeaningTitleVisible.observe(viewLifecycleOwner) {
            binding.scrollView.otherTranslationsLabel.isVisible = it
        }
        viewModel.examples.observe(viewLifecycleOwner){
            exampleAdapter.submitList(it)
        }
        viewModel.loading.observe(viewLifecycleOwner){
            binding.loadingLayout.loadingLayoutProgressBar.isVisible = it.isLoading
            binding.loadingLayout.loadingLayoutText.isVisible = it.isMessageVisible
            binding.loadingLayout.loadingLayoutText.text = it.message
            binding.loadingLayout.loadingLayoutButton.isVisible = it.isRetryButtonVisible
            binding.loadingLayout.loadingLayoutButton.text = it.retryButton
            binding.loadingLayout.loadingLayout.isVisible = it.isLoadingLayoutVisible
        }
        viewModel.navigateToMeaningScreen.observe(viewLifecycleOwner) {
            meaningScreenNavigator.navigate(it)
        }
    }
}