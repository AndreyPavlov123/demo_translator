package com.pavlov.demo_translator.common

import android.media.MediaPlayer
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class SoundInteractor @Inject constructor() {

    @Suppress("BlockingMethodInNonBlockingContext")
    fun playSound(soundUrl: String) {
        GlobalScope.launch {
            MediaPlayer().apply {
                setDataSource(soundUrl)
                prepare() // might take long! (for buffering, etc)
                start()
            }
        }
    }
}
