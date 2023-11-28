package io.mp3editor.util;

/**
 * Contains util methods for String manipulations
 */
public class StringUtil {

    private static final String ILLEGAL_FILENAME_CHARACTERS = "[/:\\*\\?\"<>\\|\\\\]";

    /**
     * Clean str removing from it {@link StringUtil#ILLEGAL_FILENAME_CHARACTERS}
     *
     * @param str that need to be cleaned
     * @return cleaned {@code str}
     */
    public static String cleanString(String str) {
        return str.replaceAll(ILLEGAL_FILENAME_CHARACTERS, "");
    }
}
