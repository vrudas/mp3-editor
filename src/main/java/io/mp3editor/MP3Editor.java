package io.mp3editor;

import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.audio.mp3.MP3FileReader;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.id3.AbstractID3v2Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.function.Function;

import static io.mp3editor.util.StringUtil.cleanString;

/**
 * Class for simply modifications of {@link MP3Editor#FILE_EXTENSION} files contained
 * in {@link MP3Editor#ROOT_DIRECTORY}
 */
public class MP3Editor {
    private final Logger logger = LoggerFactory.getLogger(MP3Editor.class);

    private static final MP3FileReader READER = new MP3FileReader();
    private static final Path ROOT_DIRECTORY = Paths.get(".");
    private static final String FILE_EXTENSION = ".mp3";

    public static void main(String[] args) throws IOException {
        MP3Editor editor = new MP3Editor();
        editor.editFiles(ROOT_DIRECTORY);
    }

    /**
     * Edit files contained in {@code directory}
     *
     * @param directory that contains files that need to be edited
     * @throws IOException
     */
    private void editFiles(Path directory) throws IOException {
        Function<Path, Optional<MP3File>> pathToMP3File = path -> {
            try {
                return Optional.ofNullable((MP3File) READER.read(path.toFile()));
            } catch (Exception e) {
                logger.error("Can't extract mp3 file", e);
            }
            return Optional.empty();
        };

        Files.walk(directory)
                .filter(path -> Files.isRegularFile(path))
                .map(pathToMP3File)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .forEach(mp3File -> {
                    AbstractID3v2Tag tag = mp3File.getID3v2Tag();
                    String artist = cleanString(tag.getFirst(FieldKey.ARTIST));
                    String title = cleanString(tag.getFirst(FieldKey.TITLE));
                    String genre = cleanString(tag.getFirst(FieldKey.GENRE));

                    createGenreFolder(directory, genre);

                    String newFileName = makeFileName(artist, title);
                    Path genreDirPath = Paths.get(String.valueOf(directory), genre);

                    moveFile(mp3File.getFile(), genreDirPath, newFileName, FILE_EXTENSION);
                });
    }

    /**
     * Move file to a new path
     *
     * @param file         current file to move
     * @param genreDirPath folder path
     * @param newFileName  new name of file
     */
    private void moveFile(File file, Path genreDirPath, String newFileName, String fileExtension) {
        Path filePath = file.toPath();
        String genreDir = String.valueOf(genreDirPath);
        Path targetPath = prepareTargetPath(genreDir, newFileName, fileExtension);

        try {
            if (Files.exists(targetPath)) {
                newFileName = deduplicateFileName(newFileName, file.getName());
                targetPath = prepareTargetPath(genreDir, newFileName, fileExtension);
            }
            Files.move(filePath, targetPath);
        } catch (IOException e) {
            String message = String.format("Cant move file:%s", newFileName);
            logger.error(message, e);
        }
    }

    /**
     * @param genreDir      directory of genre
     * @param newFileName   file new name
     * @param fileExtension file extension
     * @return targetPath
     */
    Path prepareTargetPath(String genreDir, String newFileName, String fileExtension) {
        return Paths.get(genreDir, newFileName + fileExtension);
    }

    /**
     * Creates file name from artist and title values
     *
     * @param artist value
     * @param title  value
     * @return new file name
     */
    String makeFileName(String artist, String title) {
        String fileName = artist + " - " + title;
        return cleanString(fileName);
    }

    /**
     * Create {@code genre} folder in directory represented by {@code path}
     *
     * @param path  where need to create {@code genre} folder
     * @param genre folder name
     */
    private void createGenreFolder(Path path, String genre) {
        try {
            Path genreFolder = Paths.get(String.valueOf(path), genre);
            if (!Files.exists(genreFolder)) {
                Files.createDirectory(genreFolder);
            }
        } catch (IOException e) {
            String message = String.format("Cant create folder for genre:%s", genre);
            logger.error(message, e);
        }
    }

    /**
     * @param newFileName     new name of file
     * @param currentFileName name of file
     * @return deduplicate file name
     */
    String deduplicateFileName(String newFileName, String currentFileName) {
        return newFileName + " (" + currentFileName.replace(FILE_EXTENSION, "") + ")";
    }
}
