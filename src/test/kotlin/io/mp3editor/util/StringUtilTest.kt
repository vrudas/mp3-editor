package io.mp3editor.util

import io.mp3editor.util.StringUtil.cleanString
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class StringUtilTest {
    @Test
    fun testCleanFileName() {
        val dirtyFileName = "/:\\*\\?\"<>\\|\\\\"
        val cleanFileName = cleanString(dirtyFileName)
        assertEquals("", cleanFileName)
    }

}
