package io.mp3editor

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.nio.file.Paths

class MP3EditorTest {
    // TODO: 4/18/2016 IMPLEMENT TESTS
    private lateinit var editor: MP3Editor

    @BeforeEach
    fun init() {
        editor = MP3Editor()
    }

    @Test
    fun testDeduplicateFileName() {
        val newFileName = "newFileName"
        val currentFileName = "fileName"

        val expectedFileName = "$newFileName ($currentFileName)"

        val deduplicatedFileName = editor.deduplicateFileName(newFileName, currentFileName)

        assertEquals(expectedFileName, deduplicatedFileName)
    }

    @Test
    fun testMakeFileName() {
        val firstPart = "first"
        val secondPart = "second"
        val expectedFileName = String.format("%s - %s", firstPart, secondPart)

        val fileName = editor.makeFileName(firstPart, secondPart)

        assertEquals(expectedFileName, fileName)
    }

    @Test
    fun testPrepareTargetPath() {
        val genreDir = "genre"
        val fileName = "fileName"
        val extension = ".extension"

        val expectedPath = Paths.get(genreDir, fileName + extension)

        val path = editor.prepareTargetPath(genreDir, fileName, extension)

        assertEquals(expectedPath, path)
    }
}
