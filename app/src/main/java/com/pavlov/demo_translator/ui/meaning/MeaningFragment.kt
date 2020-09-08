package com.pavlov.demo_translator.ui.meaning

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.pavlov.demo_translator.R
import com.pavlov.demo_translator.core.api.data.Word
import com.pavlov.demo_translator.databinding.FragmentMeaningBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.content_scrolling_meaning.*
import kotlinx.android.synthetic.main.fragment_meaning.*

@AndroidEntryPoint
class MeaningFragment : DialogFragment() {

    companion object {
        fun newInstance(word: Word, selectedMeaning: Int) = MeaningFragment().apply {
            arguments = Bundle().apply {
                putParcelable("meaningShortRoot", word)
                putInt("selectedMeaning", selectedMeaning)
            }
        }
    }

    private val viewModel: MeaningViewModel by viewModels()

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
        requireArguments().apply {
            viewModel.init(getParcelable("meaningShortRoot")!!, getInt("selectedMeaning"))
        }
        viewModel.title.observe(viewLifecycleOwner) {
            toolbarLayout.title = it
        }
        viewModel.image.observe(viewLifecycleOwner) {
            Glide.with(this).load(it).into(detailImage)
        }
        viewModel.closeEvent.observe(viewLifecycleOwner) {
            dismiss()
        }
    }
}