package com.pavlov.demo_translator.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.paging.ExperimentalPagingApi
import com.pavlov.demo_translator.R
import com.pavlov.demo_translator.ui.search.SearchFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@FlowPreview
@ExperimentalCoroutinesApi
@ExperimentalPagingApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, SearchFragment.newInstance())
                    .commitNow()
        }
    }
}