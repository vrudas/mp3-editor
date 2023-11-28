package io.playlisteditor

import io.playlisteditor.SetTimeCounter.extractTimes
import io.playlisteditor.SetTimeCounter.secondsBetween
import io.playlisteditor.SetTimeCounter.toLocalTime
import io.playlisteditor.SetTimeCounter.tracks
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.time.Duration
import java.time.LocalTime

internal class SetTimeCounterTest {

    @Test
    fun testSecondsBetween() {
        val secondsBetween = secondsBetween(
            LocalTime.of(0, 0, 0),
            LocalTime.of(0, 2, 56)
        )

        val expectedSecondsBetween = 176L

        assertEquals(expectedSecondsBetween, secondsBetween)
    }

    @Test
    fun testStringToLocalTime() {
        val localTime = "03:26".toLocalTime()
        assertEquals(LocalTime.of(0, 3, 26), localTime)
    }

    @Test
    fun testExtractTimes() {
        val (start, end) = "02:40-04:22 = 000".extractTimes()

        assertEquals(LocalTime.of(0, 2, 40), start)
        assertEquals(LocalTime.of(0, 4, 22), end)
    }

    @Test
    fun testCalcSetTime() {
        val sum = tracks.map { it.extractTimes() }
            .map { (start, end) -> secondsBetween(start, end) }
            .sum()

        println("sum = $sum")
        println("duration = ${Duration.ofSeconds(sum)}")
    }
}
