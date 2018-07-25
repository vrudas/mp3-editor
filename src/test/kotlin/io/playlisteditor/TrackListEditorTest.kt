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
                00:00 04 Dillytek - through the light (extended mix)
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
        ),
        arrayOf(
            TrackLists.EP05,
            """
                00:00 01 Headhunterz - The Power of Music
                00:00 02 Hardwell - Make The World Ours
                00:00 03 Stephanie - Razor Sharp
                00:00 04 D-Block & S-Te-Fan, The Pitcher & DV Rocks! - Save Our Dream
                00:00 05 Coone - Love For The Game
                00:00 06 Scope DJ - Lockdown
                00:00 07 Da Tweekaz & Neilio - Freedom
                00:00 08 Frontliner Ft. Katt Niall - (We Are) Indestructible
                00:00 09 Wildstylez - Timeless
                00:00 10 Project One - Luminosity
                00:00 11 Psyko Punkz & DJ Isaac and Sound Rush - Unbreakable
                00:00 12 Bass Modulators - Imagine
                00:00 13 Brennan Heart & Jonathan Mendelsohn - Imaginary
                00:00 14 KELTEK - Through The Night
                00:00 15 D-Block & S-Te-Fan - Above Average
                00:00 16 MYST - Before You Go
                00:00 17 Ran-D - Zombie
                00:00 18 Wildstylez - Encore
                00:00 19 Ran-D - #MyWay
                00:00 20 Wildstylez - No Time To Waste (Defqon.1 Anthem 2010)
                00:00 21 Mike NRG - Lost In Dreams (Masters Of Ceremony Remix)
            """.trimIndent()
        ),
        arrayOf(
            TrackLists.EP06,
            """
                00:00 01 Zany - Maximum Force (defqon.1 Australia 2009 Anthem)
                00:00 02 Headhunterz - Scrap Attack (DefQon.1 2009 Anthem)
                00:00 03 Wildstylez - No Time To Waste (Defqon.1 Anthem 2010)
                00:00 04 Noisecontrollers - Unite (Official Defqon.1 Anthem 2011)
                00:00 05 Headhunterz & Wildstylez Vs. Noisecontrollers - World Of Madness (Defqon.1 Anthem 2012)
                00:00 06 Brennan Heart - Scrap The System (Defqon 1 Australia 2013 Anthem)
                00:00 07 Code Black - Unleash The Beast (Official Defqon 1 Australia 2014 Anthem)
                00:00 08 Wildstylez - Unleash the Beast (Defqon 1 Chile Anthem 2015)
                00:00 09 Bass Modulators - Dragonblood (Defqon.1 Anthem 2016)
                00:00 10 Audiofreq & Code Black & Toneshifterz - Dragonblood (Defqon.1 Australia Anthem 2016)
                00:00 11 Frontliner - Dragonblood (Defqon.1 Chile Anthem 2016)
                00:00 12 Frontliner & Dillytek feat. 360 - No Guts No Glory (Defqon.1 Australia Anthem 2015)
                00:00 13 Ran-D feat. Skits Vicious - No Guts, No Glory (Defqon.1 Anthem 2015)
                00:00 14 Wildstylez - True Rebel Freedom (Defqon 1 Australia 2012 Anthem)
                00:00 15 D-Block & S-Te-Fan - Eye Of The Storm (Defqon.1 Australia Anthem 2017)
                00:00 16 Headhunterz - Save Your Scrap for Victory (Defqon 1 Australia Anthem 2010)
                00:00 17 Coone - Survival Of The Fittest (Defqon 1 Anthem 2014)
                00:00 18 Toneshifterz - Psychedelic Wasteland (Official Defqon.1 Australia Anthem 2011)
                00:00 19 Frontliner - Weekend Warriors (Official Defqon 1 2013 Anthem)
                00:00 20 Frequencerz - Victory Forever (Defqon.1 Anthem 2017)
                00:00 21 Project One - Maximum Force (Defqon.1 Anthem 2018)
            """.trimIndent()
        ),
        arrayOf(
            TrackLists.SPD_12_YEARS,
            """
                00:00 01 W&W, Hardwell ft. Lil Jon - Live The Night
                00:00 02  - ACDC - Thunderstruck (Shameless Bootleg)
                00:00 03 MAKJ & Timmy Trumpet - Party Till We Die (feat. Andrew W.K.) [Extended Mix]
                00:00 04  - Bon Jovi - Its My Life (TuneSquad Bootleg) full
                00:00 05  - DOPEDROP - We Are The Stars
                00:00 06 Dimitri Vegas & Like Mike vs. Ummet Ozcan - The Hum
                00:00 07 W&W - Bigfoot
                00:00 08 R3hab & KSHMR - Karate
                00:00 09 KSHMR & Bassjackers - Memories (feat. Sirah)
                00:00 10  - Freaks we will rock you (K3Leng Mashup)
                00:00 11 Bassjackers, L3N - Bassjackers, D'Angello & Francis - All Aboard (Dimitri Vegas, Like Mike Edit)
                00:00 12  - Cutting Crew - Died in Your Arms (TuneSquad Bootleg) Full
                00:00 13 Kenneth G + AudioTwinz - Rave-Olution
                00:00 14 Pitchback vs. Alesso vs. David Guetta vs. Linkin Park - Cosmic Dark vs. In The End (Hardwell UMF 2016 Mashup)
                00:00 15 KEVU & Luke Alive - Final Countdown 2k16
                00:00 16 Papa Roach vs Ummet Ozcan & Michael Jackson - Last Resort vs Smooth Criminal (Dimitri Vegas & Like Mike Mashup)
                00:00 17 Eurythmics - Sweat Dreams (Ummet Ozcan Remix)
                00:00 18 Snoop Dogg, Ummet Ozcan - The Next Episode (Ummet Ozcan Remix)
                00:00 19 Hardscar - Pop Mix
                00:00 20 The Prodigy - Omen
                00:00 21 The Prodigy - Voodoo People (Pendulum Radio Edit)
                00:00 22 Pendulum - Blood Sugar
                00:00 23 Da Tweekaz ft. HALIENE - Bring Me To Life
                00:00 24 Da Tweekaz - Komon
                00:00 25 Ran-D - Zombie
            """.trimIndent()
        )
    )

    @Test(dataProvider = "parseTrackListData")
    fun testParseTrackList(trackList: String, expectedTrackList: String) {
        assertEquals(trackListEditor.parseTrackListAsString(trackList), expectedTrackList)

        val artists = trackListEditor.extractArtists(trackListEditor.parseTrackList(trackList))
        println()
        println(artists.joinToString("\n"))
        println()
        println(artists.joinToString())
    }
}