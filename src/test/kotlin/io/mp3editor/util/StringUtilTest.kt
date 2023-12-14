package io.mp3editor.util;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StringUtilTest {

    private static final String EMPTY_STRING = "";

    @Test
    void testCleanFileName() {
        String dirtyFileName = "/:\\*\\?\"<>\\|\\\\";
        String cleanFileName = StringUtil.cleanString(dirtyFileName);
        assertEquals(EMPTY_STRING, cleanFileName);
    }
}
