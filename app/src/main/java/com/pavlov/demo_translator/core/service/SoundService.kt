package com.pavlov.demo_translator.core.service

import android.media.MediaPlayer
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

interface SoundService {
    fun playSound(soundUrl: String)
}

class SoundServiceImpl @Inject constructor() : SoundService {
    override fun playSound(soundUrl: String) {
        GlobalScope.launch {
            MediaPlayer().apply {
                setDataSource(soundUrl)
                prepare() // might take long! (for buffering, etc)
                start()
            }
        }
    }
}

@Module
@InstallIn(ActivityComponent::class)
abstract class SoundServiceModule {
    @Binds
    abstract fun bindSoundService(soundServiceImpl: SoundServiceImpl): SoundService
}