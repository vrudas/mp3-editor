package io.mp3editor

import io.mp3editor.util.StringUtil
import org.jaudiotagger.audio.AudioFileIO
import org.jaudiotagger.tag.FieldKey
import org.jaudiotagger.tag.datatype.Artwork
import java.io.File
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import kotlin.io.path.extension

class MP3Editor {

    companion object {
        const val FILE_EXTENSION: String = ".mp3"
    }

    fun editFiles(
        dirToFiles: Map<Path, List<Path>>,
        directory: Path
    ) {
        dirToFiles.forEach { (_, files) ->
            val songs = files.filter { it.extension == "mp3" }
            val artwork = files.firstOrNull { it.extension == "jpg" || it.extension == "jpeg" }

            songs.forEach { song ->
                val songAudioFile = AudioFileIO.read(song.toFile())
                val tag = songAudioFile.tag
                val artist = StringUtil.cleanString(tag.getFirst(FieldKey.ARTIST))
                val title = StringUtil.cleanString(tag.getFirst(FieldKey.TITLE))
                val genre = StringUtil.cleanString(tag.getFirst(FieldKey.GENRE))

                artwork?.let {
                    val artworkFromFile = Artwork.createArtworkFromFile(it.toFile())
                    tag.addField(artworkFromFile)
                }

                songAudioFile.commit()

                createGenreFolder(directory, genre)

                val newFileName = makeFileName(artist, title)
                val genreDirPath = Paths.get(directory.toString(), genre)

                moveFile(song.toFile(), genreDirPath, newFileName, FILE_EXTENSION)
            }
        }
    }


    /**
     * Move file to a new path
     *
     * @param file         current file to move
     * @param genreDirPath folder path
     * @param newFileName  new name of file
     */
    private fun moveFile(file: File, genreDirPath: Path, newFileName: String, fileExtension: String) {
        var newFileNameStr = newFileName
        val filePath = file.toPath()
        val genreDir = genreDirPath.toString()
        var targetPath = prepareTargetPath(genreDir, newFileNameStr, fileExtension)

        try {
            if (Files.exists(targetPath)) {
                newFileNameStr = deduplicateFileName(newFileNameStr, file.name)
                targetPath = prepareTargetPath(genreDir, newFileNameStr, fileExtension)
            }
            Files.move(filePath, targetPath)
        } catch (e: IOException) {
            val message = String.format("Cant move file:%s", newFileNameStr)
            System.err.println(message)
            e.printStackTrace()
        }
    }

    /**
     * @param genreDir      directory of genre
     * @param newFileName   file new name
     * @param fileExtension file extension
     * @return targetPath
     */
    fun prepareTargetPath(genreDir: String, newFileName: String, fileExtension: String): Path {
        return Paths.get(genreDir, newFileName + fileExtension)
    }

    /**
     * Creates file name from artist and title values
     *
     * @param artist value
     * @param title  value
     * @return new file name
     */
    fun makeFileName(artist: String, title: String): String {
        val fileName = "$artist - $title"
        return StringUtil.cleanString(fileName)
    }

    /**
     * Create `genre` folder in directory represented by `path`
     *
     * @param path  where need to create `genre` folder
     * @param genre folder name
     */
    private fun createGenreFolder(path: Path, genre: String) {
        try {
            val genreFolder = Paths.get(path.toString(), genre)
            if (!Files.exists(genreFolder)) {
                Files.createDirectory(genreFolder)
            }
        } catch (e: IOException) {
            val message = String.format("Cant create folder for genre:%s", genre)
            System.err.println(message)
            e.printStackTrace()
        }
    }

    /**
     * @param newFileName     new name of file
     * @param currentFileName name of file
     * @return deduplicate file name
     */
    fun deduplicateFileName(newFileName: String, currentFileName: String): String {
        return newFileName + " (" + currentFileName.replace(FILE_EXTENSION, "") + ")"
    }
}

fun main() {
    val directory = Paths.get(".")
    val mp3Editor = MP3Editor()

    val trackDirectoryVisitor = TrackDirectoryVisitor()
    Files.walkFileTree(directory, trackDirectoryVisitor)

    val directoriesToFiles = trackDirectoryVisitor.dirToFiles

    mp3Editor.editFiles(directoriesToFiles, directory)
}
