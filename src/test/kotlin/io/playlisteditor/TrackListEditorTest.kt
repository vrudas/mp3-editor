package io.playlisteditor

import org.testng.Assert.assertEquals
import org.testng.annotations.BeforeMethod
import org.testng.annotations.DataProvider
import org.testng.annotations.Test

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
                00:00 01 Headhunterz - Destiny
                00:00 02 Frontliner feat. John Harris - Halos
                00:00 03 Isaac - DJ Ease My Mind
                00:00 04 The Prophet - One Moment
                00:00 05 Bass Modulators - Oxygen
                00:00 06 Da Tweekaz - Komon
                00:00 07 Zatox - For Ever
                00:00 08 Da Tweekaz & Coone - Born in the 80's
                00:00 09 Toneshifterz Ft. Chris Madin - Parachutes
                00:00 10 Atmozfears feat. David Spekter - Release
                00:00 11 Project One - Luminosity
                00:00 12 Frontliner Ft. Ellie - Lose the Style
                00:00 13 Armin van Buuren & Andrew Rayel - EIFORYA (Bass Modulators Remix)
                00:00 14 Brennan Heart & Wildstylez - Lose My Mind
                00:00 15 D-Block & S-Te-Fan - Angels & Demons
                00:00 16 Showtek & Tnt - Mellow
                00:00 17 DJ Stephanie - Sweet Disposition
                00:00 18 Da Tweekaz Ft. Oscar - Break the Spell
                00:00 19 Project One - Project 1 (Sound Rush Remix)
                00:00 20 Hardwell - Spaceman (Headhunterz Remix)
            """.trimIndent()
        ),
        arrayOf(
            TrackLists.EP02,
            """
                00:00 01 Coone feat. K19 - Times Gettin' Hard
                00:00 02 Killaheadz vs ZolotoFM - Teodor Fest Anthem
                00:00 03 Abyss and Judge - Hardstyle Revolution (Showtek Remix)
                00:00 04 Noisecontrollers - Spirit Of Hardstyle
                00:00 05 JNXD - Hard Is My Style
                00:00 06 Toneshifterz And Code Black - About The Music
                00:00 07 Hard Driver - Hardstyle 24/7
                00:00 08 The Beholder & Zany - Bleeding for the Harder Styles
                00:00 09 Showtek ft. MC Stretch - Freak
                00:00 10 Headhunterz - Megasound
                00:00 11 Showtek - FTS
                00:00 12 Zatox - My Strength Is Hardstyle
                00:00 13 Coone - Words from the Gang (D-Block & S-te-Fan Remix)
                00:00 14 Hardstyle Masterz ft. Max Enforcer - Respect
                00:00 15 Headhunterz & Wildstylez - Down With The Hardstyle (Credible Mix)
                00:00 16 Ran-D & Adaro - My Name Is Hardstyle
                00:00 17 Brennan Heart & TNT - It's My Style
                00:00 18 Sound Rush ft. Eurielle - Back To The Roots
                00:00 19 The Prophet - My Religion
                00:00 20 Brennan Heart & the Prophet - Wake Up
            """.trimIndent()
        ),
        arrayOf(
            TrackLists.EP03,
            """
                00:00 01 A-lusion - Beat The Bridge (Official Anthem 2012)
                00:00 02 The Pitcher - Music to Keep Me Alive
                00:00 03 Noisecontrollers - Gimme Love
                00:00 04 Code Black - Red Planet
                00:00 05 Bass Modulators - Mantra
                00:00 06 Wasted Penguinz - Melancholia
                00:00 07 Technoboy - Rage (Frontliner Remix)
                00:00 08 Audiofreq & The Prophet feat. Teddy - Bring Me Down
                00:00 09 Wild Motherfuckers - Wild Wild West
                00:00 10 Dr Rude Feat. MC DL - The Way I Follow
                00:00 11 Coone & Da Tweekaz - D.W.X
                00:00 12 Headhunterz Ft. Malukah - Reignite
                00:00 13 Sephyx - Imagination
                00:00 14 Audiotricz - Let There Be Light
                00:00 15 Technoboy - Ti Sento
                00:00 16 Digital Punk & Profyler - Bringing the Funk (Zatox Remix)
                00:00 17 Frontliner - You Got Me Rocking
                00:00 18 Technoboy, Tuneboy & Isaac - Power Hour
                00:00 19 Evil Activities & Endymion Ft. E-Life - Broken
            """.trimIndent()
        ),
        arrayOf(
            TrackLists.EP04,
            """
                00:00 01 Adrenalize - Secrets of Time
                00:00 02 Frontliner - Keep It Up
                00:00 03 Kodex feat. Steklo - I'm Alive
                00:00 04 Dillytek - Through The Light
                00:00 05 Wildstylez - I See Stars
                00:00 06 Noisecontrollers - So High
                00:00 07 D-Block & S-Te-Fan - Rebel
                00:00 08 Brennan Heart - We Can Escape (Intents Anthem 2012)
                00:00 09 DJ Isaac - Burn (Sub Zero Project Remix)
                00:00 10 Hard Driver - Welcome
                00:00 11 Kutski Vs Bioweapon - One
                00:00 12 Aztech Ft. Nikkita - The Tribe (2014 Mix)
                00:00 13 Zatox & The R3belz - Odissea 2011
                00:00 14 Da Tweekaz - The Groove
                00:00 15 Wildstylez - A Complexe Situation
                00:00 16 Sound Rush - Mystical Expedition
                00:00 17 Stuback - Whistle
                00:00 18 Kasparov - Trapped in this World
            """.trimIndent()
        )
    )

    @Test(dataProvider = "parseTrackListData")
    fun testParseTrackList(trackList: String, expectedTrackList: String) {
        assertEquals(trackListEditor.parseTrackList(trackList), expectedTrackList)
    }
}