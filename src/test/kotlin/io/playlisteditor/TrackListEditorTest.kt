package io.playlisteditor

import org.testng.annotations.BeforeMethod
import org.testng.annotations.DataProvider
import org.testng.annotations.Test
import kotlin.test.assertEquals

class TrackListEditorTest {

    private lateinit var trackListEditor: TrackListEditor

    @BeforeMethod
    fun setUp() {
        trackListEditor = TrackListEditor()
    }

    @DataProvider(name = "parseTrackListData")
    fun parseTrackListData(): Array<Array<String>> = arrayOf(
        arrayOf("", ""),
        arrayOf(
            TrackLists.EP01,
            """
                01 Headhunterz - Destiny (Pro Mix)
                02 Frontliner feat. John Harris - Halos
                03 Isaac - DJ Ease My Mind (Extended Version)
                04 The Prophet - One Moment
                05 Bass Modulators - Oxygen (Original Mix)
                06 Da Tweekaz - Komon (Extended Mix)
                07 Zatox - For Ever (Extended Mix)
                08 Da Tweekaz & Coone - Born in the 80's
                09 Toneshifterz Ft. Chris Madin - Parachutes
                10 Atmozfears feat. David Spekter - Release (Original Mix)
                11 Project One - Luminosity
                12 Frontliner Ft. Ellie - Lose the Style
                13 Armin van Buuren & Andrew Rayel - EIFORYA (Bass Modulators Remix)
                14 Brennan Heart & Wildstylez - Lose My Mind
                15 D-Block & S-Te-Fan - Angels & Demons
                16 Showtek & Tnt - Mellow (Extended Mix)
                17 DJ Stephanie - Sweet Disposition (Original Version)
                18 Da Tweekaz Ft. Oscar - Break the Spell
                19 Project One - Project 1 (Sound Rush Remix)
                20 Hardwell - Spaceman (Headhunterz Remix)
            """.trimIndent()
        )
    )

    @Test(dataProvider = "parseTrackListData")
    fun testParseTrackList(trackList: String, expectedTrackList: String) {
        assertEquals(trackListEditor.parseTrackList(trackList), expectedTrackList)
    }
}