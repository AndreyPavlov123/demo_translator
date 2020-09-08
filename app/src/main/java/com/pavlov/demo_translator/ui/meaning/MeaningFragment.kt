package com.pavlov.demo_translator.ui.meaning

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
import com.pavlov.demo_translator.R
import com.pavlov.demo_translator.databinding.FragmentMeaningBinding
import com.pavlov.demo_translator.ui.meaning.adapter.ExampleAdapter
import com.pavlov.demo_translator.ui.model.SelectedMeaningModel
import com.pavlov.demo_translator.ui.search.adapter.MeaningAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.common_loading.*
import kotlinx.android.synthetic.main.content_scrolling_meaning.*
import kotlinx.android.synthetic.main.fragment_meaning.*

@AndroidEntryPoint
class MeaningFragment : DialogFragment() {

    companion object {
        fun newInstance(selectedMeaning: SelectedMeaningModel) = MeaningFragment().apply {
            arguments = Bundle().apply {
                putParcelable(MeaningViewModel.SELECTED_MEANING_TAG, selectedMeaning)
            }
        }
    }

    private val viewModel: MeaningViewModel by viewModels()
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
        return FragmentMeaningBinding.inflate(inflater, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar.setNavigationOnClickListener {
            viewModel.closeButtonClicked()
        }
        fab.setOnClickListener {
            viewModel.playButtonClicked()
        }
        loadingLayoutButton.setOnClickListener {
            viewModel.retryButtonClicked()
        }

        otherMeanings.adapter = otherMeaningsAdapter
        examples.adapter = exampleAdapter

        var isToolbarShown = false

        // scroll change listener begins at Y = 0 when image is fully collapsed
        scrollView.setOnScrollChangeListener(
            NestedScrollView.OnScrollChangeListener { _, _, scrollY, _, _ ->

                // User scrolled past image to height of toolbar and the title text is
                // underneath the toolbar, so the toolbar should be shown.
                val shouldShowToolbar = scrollY > toolbar.height

                // The new state of the toolbar differs from the previous state; update
                // appbar and toolbar attributes.
                if (isToolbarShown != shouldShowToolbar) {
                    isToolbarShown = shouldShowToolbar

                    // Use shadow animator to add elevation if toolbar is shown
                    //appbar.isActivated = shouldShowToolbar

                    // Show the plant name if toolbar is shown
                    toolbarLayout.isTitleEnabled = shouldShowToolbar
                }
            }
        )
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.title.observe(viewLifecycleOwner) {
            toolbarLayout.title = it
        }
        viewModel.word.observe(viewLifecycleOwner) {
            word.text = it
        }
        viewModel.transcription.observe(viewLifecycleOwner) {
            transcription.text = it
        }
        viewModel.translation.observe(viewLifecycleOwner) {
            translation.text = it
        }
        viewModel.definition.observe(viewLifecycleOwner) {
            definition.text = it
        }
        viewModel.definitionVisible.observe(viewLifecycleOwner) {
            definition.isVisible = it
        }
        viewModel.image.observe(viewLifecycleOwner) {
            Glide.with(this).load(it).into(detailImage)
        }
        viewModel.closeEvent.observe(viewLifecycleOwner) {
            dismiss()
        }
        viewModel.snackbarEvent.observe(viewLifecycleOwner) {
            Snackbar.make(coordinatorLayout, it, Snackbar.LENGTH_LONG).show()
        }
        viewModel.openMeaningScreenEvent.observe(viewLifecycleOwner){
            newInstance(it).show(childFragmentManager, "MeaningFragment$it")
        }
        viewModel.otherMeanings.observe(viewLifecycleOwner){
            otherMeaningsAdapter.submitList(it)
        }
        viewModel.otherMeaningTitleVisible.observe(viewLifecycleOwner) {
            otherTranslationsLabel.isVisible = it
        }
        viewModel.examples.observe(viewLifecycleOwner){
            exampleAdapter.submitList(it)
        }
        viewModel.loading.observe(viewLifecycleOwner){
            loadingLayoutProgressBar.isVisible = it.isLoading
            loadingLayoutText.isVisible = it.isMessageVisible
            loadingLayoutText.text = it.message
            loadingLayoutButton.isVisible = it.isRetryButtonVisible
            loadingLayoutButton.text = it.retryButton
            loadingLayout.isVisible = it.isLoadingLayoutVisible
        }
    }
}