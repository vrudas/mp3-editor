package io.mp3editor

import java.nio.file.Path
import kotlin.io.path.extension
import kotlin.io.path.isRegularFile

typealias Directory = Path
typealias TrackFile = Path

private val trackRelatedFilesExtensions = setOf(
    "mp3",
    "jpg",
    "jpeg"
)

fun TrackFile.isTrackRelatedFile(): Boolean {
    return isRegularFile() && extension in trackRelatedFilesExtensions
}

