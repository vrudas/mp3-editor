package io.mp3editor.util;

/**
 * Contains util methods for String manipulations
 */
public class StringUtil {

    private static final String ILLEGAL_FILENAME_CHARACTERS = "[/:\\*\\?\"<>\\|\\\\]";

    /**
     * Clean fileName removing from it {@link StringUtil#ILLEGAL_FILENAME_CHARACTERS}
     *
     * @param fileName that need to be cleaned
     * @return cleaned {@code fileName}
     */
    public static String cleanString(String fileName) {
        return fileName.replaceAll(ILLEGAL_FILENAME_CHARACTERS, "");
    }
}
