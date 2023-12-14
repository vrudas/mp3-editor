package io.mp3editor.util

/**
 * Contains util methods for String manipulations
 */
object StringUtil {
    private const val ILLEGAL_FILENAME_CHARACTERS = "[/:\\*\\?\"<>\\|\\\\]"

    /**
     * Clean str removing from it [StringUtil.ILLEGAL_FILENAME_CHARACTERS]
     *
     * @param str that need to be cleaned
     * @return cleaned `str`
     */
    @JvmStatic
    fun cleanString(str: String): String {
        return str.replace(ILLEGAL_FILENAME_CHARACTERS.toRegex(), "")
    }
}
