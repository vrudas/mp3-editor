package io.mp3editor.util;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

@Test
public class StringUtilTest {

    private static final String EMPTY_STRING = "";

    @Test
    public void testCleanFileName() {
        String dirtyFileName = "/:\\*\\?\"<>\\|\\\\";
        String cleanFileName = StringUtil.cleanFileName(dirtyFileName);
        assertEquals(cleanFileName, EMPTY_STRING);
    }
}
