package io.playlisteditor

fun main(args: Array<String>) {
    val trackListEditor = TrackListEditor()
    println(trackListEditor.parseTrackList(TrackLists.EP02))
}

class TrackListEditor {

    fun parseTrackList(trackList: String): String =
        trackList.split("\n")
            .drop(1)
            .map { it.split("\t") }
            .map { Track(it[0].toByte(), it[1], it[2]) }
            .sortedBy { it.number }
            .joinToString(separator = "\n") { it.toTrackListString() }
}

data class Track(val number: Byte, val title: String, val artist: String) {

    fun toTrackListString(): String = "${numberToString()} $artist - ${removeTrackTypeFromTitle()}".trim()

    private fun numberToString() = if (number in 0..9) "0$number" else number.toString()

    private fun removeTrackTypeFromTitle(): String {
        val trackMixType = TrackMixType.values().firstOrNull {
            title.contains(it.textValue, ignoreCase = true)
        }

        return trackMixType?.let { title.replace(it.textValue, "") } ?: title
    }

}

enum class TrackMixType(val textValue: String) {
    ORIGINAL_MIX("(Original Mix)"),
    EXTENDED_MIX("(Extended Mix)"),
    EXTENDED("(Extended)"),
    EXTENDED_VERSION("(Extended Version)"),
    ORIGINAL_VERSION("(Original Version)"),
    EXTENDED_EDIT("(Extended Edit)"),
    PRO_MIX("(Pro Mix)"),
    EDIT("(Edit)"),
    RADIO_EDIT("(Radio Edit)")
}

object TrackLists {
    val EP01 = """
            #	Track Title	Artist	BPM	Key	Album	Genre	Time	Rating	Comments
            1	Destiny (Pro Mix)	Headhunterz	150.00	05A	Destiny	L4H	04:00	     	01
            2	Halos	Frontliner feat. John Harris	150.00	12A	Fear FM Hardstyle Top 40 December 2012	L4H	05:20	     	02
            3	DJ Ease My Mind (Extended Version)	Isaac	150.00	01A	DJ Ease My Mind	L4H	05:58	     	03
            4	One Moment	The Prophet	150.00	03A	One Moment WEB	L4H	05:55	     	04
            5	Oxygen (Original Mix)	Bass Modulators	148.00	03A	Oxygen	L4H	04:00	     	05
            6	Komon (Extended Mix)	Da Tweekaz	150.11	04A	Komon	L4H	04:37	     	06
            7	For Ever (Extended Mix)	Zatox	150.00	05A	For Ever	L4H	05:23	     	07
            8	Born in the 80's	Da Tweekaz & Coone	150.00	06A	Born in the 80's WEB	L4H	06:29	     	08
            9	Parachutes	Toneshifterz Ft. Chris Madin	150.01	06A	Parachutes WEB	L4H	06:49	     	09
            10	Release (Original Mix)	Atmozfears feat. David Spekter	150.00	07A	Release	L4H	04:29	     	10
            11	Luminosity	Project One	150.00	08A	EP I	L4H	04:29	     	11
            12	Lose the Style	Frontliner Ft. Ellie	150.06	08A	Producers Mind Album Sampler 02 WEB	L4H	05:08	     	12
            13	EIFORYA (Bass Modulators Remix)	Armin van Buuren & Andrew Rayel	150.00	08A	EIFORYA - Bass Modulators Remix	L4H	05:58	     	13
            14	Lose My Mind	Brennan Heart & Wildstylez	150.00	08A	Fear FM Hardstyle Top 100 2012	L4H	05:24	     	14
            15	Angels & Demons	D-Block & S-Te-Fan	150.00	09A	Angels & Demons WEB	L4H	03:50	     	15
            16	Mellow (Extended Mix)	Showtek & Tnt	150.00	10A	Mellow	L4H	04:31	     	16
            17	Sweet Disposition (Original Version)	DJ Stephanie	150.00	10A	Fear FM Hardstyle Top 40 July 2012	Hardstyle	04:31	     	17
            18	Break the Spell	Da Tweekaz Ft. Oscar	150.00	11A	Time 2 Shine WEB	L4H	05:16	     	18
            19	Project 1 (Sound Rush Remix)	Project One	150.00	01A	EP I	L4H	05:40	     	19
            20	Spaceman (Headhunterz Remix)	Hardwell	128.19	11A	Headhunterz : Remixes, Free,Other	L4H	05:41	     	20
        """.trimIndent()

    val EP02 = """
        #	Track Title	Artist	BPM	Key	Album	Genre	Time	Rating	Comments
        1	Times Gettin' Hard (Original Mix)	Coone feat. K19	150.00	01A	Times Gettin' Hard WEB	L4H	05:36	     	01
        2	Teodor Fest Anthem	Killaheadz vs ZolotoFM	149.99	03A	Teodor HARDSTYLE Mixed by T-DJ	Hardstyle Anthem	07:24	     	02
        3	Hardstyle Revolution (Showtek Remix)	Abyss and Judge	147.00	03A	Fear FM Hardstyle Top 40 2009-07	Hardstyle	07:54	     	03
        4	Spirit Of Hardstyle (Extended)	Noisecontrollers	149.93	04A	Spirit Of Hardstyle WEB	Hardstyle	06:43	     	04
        5	Hard Is My Style (Extended Mix)	JNXD	150.00	04A	Hard Is My Style WEB	Hardstyle	03:43	     	05
        6	About The Music (Original Mix)	Toneshifterz And Code Black	150.00	04A	About The Music WEB	L4H	04:28	     	06
        7	Hardstyle 24/7 (Extended Mix)	Hard Driver	152.04	04B	Hardstyle 24/7 WEB	Hardstyle	03:37	     	07
        8	Bleeding for the Harder Styles	The Beholder & Zany	150.00	05A	Bleeding for the Harder Styles (The Official Decibel Anthem 2008) - EP	Hardstyle Anthem	06:22	     	08
        9	Freak	Showtek ft. MC Stretch	150.00	06A	Magic City Unleash The Dragon CD2 Mixed By Toneshifterz	L4H	03:55	     	09
        10	Megasound	Headhunterz	150.00	06A	Megasound WEB	Hardstyle	04:55	     	10
        11	FTS (Original Mix)	Showtek	146.00	06A	DutchMaster Works Collection Vol 1 (Unmixed plus Mix)	Hardstyle	05:27	     	11
        12	My Strength Is Hardstyle (Extended Mix)	Zatox	150.00	07B	My Strength Is Hardstyle WEB	L4H	04:15	     	12
        13	Words from the Gang (D-Block & S-te-Fan Remix)	Coone	146.09	08A	My Dirty Workz	Hardstyle	05:20	     	13
        14	Respect	Hardstyle Masterz ft. Max Enforcer	150.00	08A	Fear FM Hardstyle Top 40 2009-11	L4H	05:38	     	14
        15	Down With The Hardstyle (Credible Mix)	Headhunterz & Wildstylez	152.00	08A	Hard Bass 2011	L4H	04:22	     	15
        16	My Name Is Hardstyle	Ran-D & Adaro	144.27	10A	My Name Is Hardstyle	L4R	07:18	     	16
        17	It's My Style (Original Mix)	Brennan Heart & TNT	150.00	11A	It's My Style WEB	Hardstyle	05:15	     	17
        18	Back To The Roots (Original Mix)	Sound Rush ft. Eurielle	150.01	12A	Back To The Roots WEB	L4H	04:44	     	18
        19	My Religion	The Prophet	150.00	01A	SSL009	L4H	06:19	     	19
        20	Wake Up	Brennan Heart & the Prophet	150.00	03A	Wake Up	Classic Hardstyle	05:53	     	20
    """.trimIndent()

    val EP03 = """
        #	Track Title	Artist	BPM	Key	Album	Genre	Time	Rating	Comments
        1	Beat The Bridge (Official Anthem 2012)	A-lusion	150.00	01A	Beat The Bridge (Official Anthem 2012) WEB	Hardstyle Anthem	05:07	     	01
        2	Music to Keep Me Alive (Original Mix)	The Pitcher	150.00	04A	Fear FM Hardstyle Top 100 2012	Hardstyle	04:33	     	02
        3	Gimme Love	Noisecontrollers	150.00	04A	Fear FM Hardstyle Top 40 2011-05	Hardstyle	04:35	     	03
        4	Red Planet	Code Black	150.00	04A	Fear FM Hardstyle Top 40 2011-12	Hardstyle	05:44	     	04
        5	Mantra (Original Mix)	Bass Modulators	150.00	04A	Mantra WEB	Hardstyle	05:23	     	05
        6	Melancholia (Original Mix)	Wasted Penguinz	150.00	04A	Melancholia / Far From Reality WEB	Euphoric Hardstyle	05:44	     	06
        7	Rage (Frontliner Remix) (Original Mix)	Technoboy	150.00	05A	Fantasy Island Festival 2012	L4H	06:32	     	07
        8	Bring Me Down	Audiofreq & The Prophet feat. Teddy	150.00	06A	Bring Me Down WEB	L4H	05:30	     	08
        9	Wild Wild West	Wild Motherfuckers	150.00	06A	Q-Dance Hardstyle Top 40 2013-10	L4R	05:12	     	09
        10	The Way I Follow (Original Mix)	Dr Rude Feat. MC DL	150.06	06A	The Way I Follow	Freestyle Hardstyle	04:07	     	10
        11	D.W.X	Coone & Da Tweekaz	150.00	06A	Traveling	L4H	06:15	     	11
        12	Reignite	Headhunterz Ft. Malukah	150.00	06A	The Leaked EP WEB	L4H	06:24	     	12
        13	Imagination	Sephyx	150.07	09A	Q-Dance Hardstyle Top 40 2016-10	Hardstyle	04:04	     	13
        14	Let There Be Light (Extended Mix)	Audiotricz	150.00	09A	Let There Be Light WEB	Hardstyle	04:41	     	14
        15	Ti Sento	Technoboy	150.05	10A	Fear FM Hardstyle Top 40 2009-04	L4H	07:22	     	15
        16	Bringing the Funk (Zatox Remix)	Digital Punk & Profyler	149.97	11A	Fear FM Hardstyle Top 40 2011-07	L4H	05:26	     	16
        17	You Got Me Rocking	Frontliner	150.00	11A	Q-Dance Hardstyle Top 40 2014-04	L4H	06:02	     	17
        18	Power Hour	Technoboy, Tuneboy & Isaac	150.00	01A	Victory Forever EP WEB	Hardstyle	03:39	     	18
        19	Broken	Evil Activities & Endymion Ft. E-Life	82.46	01A	Intents Festival 2011 Hardcore Edition WEB	Hardcore	05:19	     	19
    """.trimIndent()
}