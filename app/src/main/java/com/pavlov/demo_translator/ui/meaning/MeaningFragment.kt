package com.pavlov.demo_translator.ui.meaning

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import com.pavlov.demo_translator.R
import com.pavlov.demo_translator.core.api.MeaningShortRoot
import com.pavlov.demo_translator.databinding.FragmentMeaningBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_meaning.*

@AndroidEntryPoint
class MeaningFragment : DialogFragment() {

    companion object {
        fun newInstance(meaningShortRoot: MeaningShortRoot, selectedMeaning: Int) = MeaningFragment().apply {
            arguments = Bundle().apply {
                putParcelable("data", meaningShortRoot)
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
            dismiss()
        }
        fab.setOnClickListener {
            Snackbar.make(it, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.setData(requireArguments().getParcelable("data")!!)
        viewModel.title.observe(viewLifecycleOwner) {
            toolbar_layout.title = it
        }
    }
}