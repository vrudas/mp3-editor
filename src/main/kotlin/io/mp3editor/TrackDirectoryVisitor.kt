package io.mp3editor

import java.nio.file.FileVisitResult
import java.nio.file.Path
import java.nio.file.SimpleFileVisitor
import java.nio.file.attribute.BasicFileAttributes

class TrackDirectoryVisitor(
    val dirToFiles: MutableMap<Directory, MutableList<Path>> = mutableMapOf()
) : SimpleFileVisitor<Path>() {

    override fun preVisitDirectory(dir: Path, attrs: BasicFileAttributes): FileVisitResult {
        dirToFiles.computeIfAbsent(dir) { mutableListOf() }

        return super.preVisitDirectory(dir, attrs)
    }

    override fun visitFile(file: Path, attrs: BasicFileAttributes): FileVisitResult {
        if (file.isTrackRelatedFile()) {
            dirToFiles[file.parent]?.add(file)
        }

        return super.visitFile(file, attrs)
    }
}

