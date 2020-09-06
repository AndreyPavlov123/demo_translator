package com.pavlov.demo_translator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pavlov.demo_translator.ui.main.MainFragment
import com.pavlov.demo_translator.ui.meaning.MeaningFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow()
        }

        MeaningFragment().show(supportFragmentManager, "MeaningFragment")
    }
}