package io.mp3editor;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.testng.Assert.assertEquals;

@Test
public class MP3EditorTest {
    // TODO: 4/18/2016 IMPLEMENT TESTS

    private MP3Editor editor;

    @BeforeSuite
    public void init() {
        editor = new MP3Editor();
    }

    @Test
    public void testDeduplicateFileName() {
        String newFileName = "newFileName";
        String currentFileName = "fileName";

        String expectedFileName = String.format("%s (%s)", newFileName, currentFileName);

        String deduplicatedFileName = editor.deduplicateFileName(newFileName, currentFileName);

        assertEquals(deduplicatedFileName, expectedFileName);
    }

    @Test
    public void testMakeFileName() {
        String firstPart = "first";
        String secondPart = "second";
        String expectedFileName = String.format("%s - %s", firstPart, secondPart);

        String fileName = editor.makeFileName(firstPart, secondPart);

        assertEquals(fileName, expectedFileName);
    }

    @Test
    public void testPrepareTargetPath() {
        String genreDir = "genre";
        String fileName = "fileName";
        String extension = ".extension";

        Path expectedPath = Paths.get(genreDir, fileName + extension);

        Path path = editor.prepareTargetPath(genreDir, fileName, extension);

        assertEquals(path, expectedPath);
    }
}
