package com.pavlov.demo_translator

import com.pavlov.demo_translator.core.api.data.correctUrl
import org.junit.Test

import org.junit.Assert.*
import java.net.URI

class ToolsUnitTest {

    @Test
    fun urlCorrection_IsCorrect() {
        val uri = URI.create("//d.android.com/tools/testing".correctUrl())
        assertEquals(uri.scheme, "https")
    }
}