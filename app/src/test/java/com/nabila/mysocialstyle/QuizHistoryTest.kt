package com.nabila.mysocialstyle

import com.nabila.mysocialstyle.ui.history.HistoryUtils
import junit.framework.TestCase.assertTrue
import org.junit.Test
import org.junit.Assert.*

class QuizHistoryTest {

    @Test
    fun testAmiableDescription() {
        val result = HistoryUtils.getStyleDescription("amiable")
        assertTrue(result.contains("Amiable adalah orang yang sabar"))
    }

    @Test
    fun testAnalyticalDescription() {
        val result = HistoryUtils.getStyleDescription("analytical")
        assertTrue(result.contains("logis"))
    }

    @Test
    fun testDriverDescription() {
        val result = HistoryUtils.getStyleDescription("driver")
        assertTrue(result.contains("tegas"))
    }

    @Test
    fun testExpressiveDescription() {
        val result = HistoryUtils.getStyleDescription("expressive")
        assertTrue(result.contains("komunikatif"))
    }

    @Test
    fun testUnknownStyleDescription() {
        val result = HistoryUtils.getStyleDescription("random-style")
        assertEquals("Tidak diketahui", result)
    }
}
