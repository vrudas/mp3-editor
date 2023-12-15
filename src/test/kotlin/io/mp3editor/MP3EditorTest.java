package io.mp3editor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MP3EditorTest {
    // TODO: 4/18/2016 IMPLEMENT TESTS

    private MP3EditorV2 editor;

    @BeforeEach
    public void init() {
        editor = new MP3EditorV2();
    }

    @Test
    void testDeduplicateFileName() {
        String newFileName = "newFileName";
        String currentFileName = "fileName";

        String expectedFileName = String.format("%s (%s)", newFileName, currentFileName);

        String deduplicatedFileName = editor.deduplicateFileName(newFileName, currentFileName);

        assertEquals(expectedFileName, deduplicatedFileName);
    }

    @Test
    void testMakeFileName() {
        String firstPart = "first";
        String secondPart = "second";
        String expectedFileName = String.format("%s - %s", firstPart, secondPart);

        String fileName = editor.makeFileName(firstPart, secondPart);

        assertEquals(expectedFileName, fileName);
    }

    @Test
    void testPrepareTargetPath() {
        String genreDir = "genre";
        String fileName = "fileName";
        String extension = ".extension";

        Path expectedPath = Paths.get(genreDir, fileName + extension);

        Path path = editor.prepareTargetPath(genreDir, fileName, extension);

        assertEquals(expectedPath, path);
    }
}
