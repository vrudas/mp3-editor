package io.playlisteditor

fun main() {
    val trackListEditor = TrackListEditor()
    println(trackListEditor.parseTrackList(TrackLists.EP02))
}

class TrackListEditor {

    fun parseTrackListAsString(trackList: String) =
        parseTrackList(trackList).joinToString(separator = "\n") { it.toTrackListString() }

    fun parseTrackList(trackList: String): List<Track> {
        return trackList.split("\n")
            .drop(1)
            .map { it.split("\t") }
            .map { (number, title, artist) -> Track(number.toByte(), title, artist) }
            .sortedBy { it.number }
    }

    fun extractArtists(tracks: List<Track>): List<String> = tracks.map { it.artist }.distinct()
}

data class Track(val number: Byte, val title: String, val artist: String) {

    fun toTrackListString(): String = "00:00 ${numberToString()} $artist - ${removeTrackTypeFromTitle()}".trim()

    private fun numberToString() = if (number in 0..9) "0$number" else number.toString()

    private fun removeTrackTypeFromTitle(): String {
        val trackMixType = TrackMixType.entries.firstOrNull {
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

    val EP04 = """
        #	Track Title	Artist	BPM	Key	Album	Genre	Time	Rating	Comments
        1	Secrets of Time	Adrenalize	150.00	11A	Secrets of Time	Techno	05:02	     	00
        2	Keep It Up	Frontliner	150.00	01A	Fear FM Hardstyle Top 100 2012	Hardstyle	04:43	     	01
        3	I'm Alive	Kodex feat. Steklo	150.00	03A	The First Impact EP	L4H	05:23	     	02
        4	through the light (extended mix)	Dillytek	150.00	04A	Through The Light	Hardstyle	04:07	     	03
        5	I See Stars	Wildstylez	150.00	05A		L4H	04:44	     	04
        6	So High (Original Mix)	Noisecontrollers	150.00	06A	FearFM Hardstyle Top 40 March 2012	L4H	05:38	     	05
        7	Rebel (Original Mix)	D-Block & S-Te-Fan	150.00	06A	Rebel WEB	L4H	05:14	     	06
        8	We Can Escape (Intents Anthem 2012) (Original Mix)	Brennan Heart	150.00	06A	We Can Escape (Intents Anthem 2012) WEB	Hardstyle Anthem	05:04	     	07
        9	Burn (Sub Zero Project Remix) (Extended Mix)	DJ Isaac	150.00	08A	Burn (Sub Zero Project Remix) WEB	Techno	04:55	     	08
        10	Welcome (Extended Mix)	Hard Driver	150.00	09A	Welcome WEB	Techno	04:34	     	09
        11	One	Kutski Vs Bioweapon	150.00	09A	Fear FM Hardstyle Top 40 2009-08	Hardstyle	06:22	     	10
        12	The Tribe (2014 Mix) (Edit)	Aztech Ft. Nikkita	150.00	10A	We R Hardstyle Yearmix 2014 WEB	Techno	04:41	     	11
        13	Odissea 2011	Zatox & The R3belz	150.00	11A	Odissea 2011 WEB	Hardstyle	05:00	     	12
        14	The Groove	Da Tweekaz	150.00	11A	Fear FM Hardstyle Top 40 2011-11	Hardstyle	05:52	     	13
        15	A Complexe Situation	Wildstylez	150.00	12A	In & Out	Techno	05:44	     	14
        16	Mystical Expedition (Extended Mix)	Sound Rush	150.00	12A	Mystical Expedition	Techno	03:31	     	15
        17	Whistle	Stuback	150.00	11B	Whistle / Soundwave WEB	Techno	06:05	     	16
        18	Trapped in this World	Kasparov	82.28	01A	Keep Going WEB	Hardcore	06:06	     	17
    """.trimIndent()

    val EP05 = """
        #	Track Title	Artist	BPM	Key	Album	Genre	Time	Rating	Comments
        1	The Power of Music	Headhunterz	150.00	01A	The Leaked EP WEB	L4H	05:44	     	01
        2	Make The World Ours	Hardwell	150.02	04A	Victory Forever EP WEB	L4H	04:08	     	02
        3	Razor Sharp (Extended Mix)	Stephanie	150.00	04A	Razor Sharp	Hardstyle	04:25	     	03
        4	Save Our Dream	D-Block & S-Te-Fan, The Pitcher & DV Rocks!	150.00	06A	Q-Dance Hardstyle Top 40 2013-05	L4H	04:51	     	04
        5	Love For The Game	Coone	150.00	08A	Q-Dance Hardstyle Top 100 2015	L4H	04:12	     	05
        6	Lockdown	Scope DJ	143.00	08A	Lockdown / Protocol	Classic Hardstyle	06:54	     	06
        7	Freedom	Da Tweekaz & Neilio	150.00	08A	Q-Dance Hardstyle Top 40 2016-02	L4H	06:34	     	07
        8	(We Are) Indestructible (Original Mix)	Frontliner Ft. Katt Niall	150.00	08A	(We Are) Indestructible WEB	L4H	05:38	     	08
        9	Timeless	Wildstylez	150.00	08A	Q-Dance Hardstyle Top 40 2013-04	L4H	05:08	     	09
        10	Luminosity	Project One	150.00	08A	EP I	L4H	04:29	*****	10
        11	Unbreakable (Extended Mix)	Psyko Punkz & DJ Isaac and Sound Rush	149.99	09A	Unbreakable WEB	L4H	03:42	     	11
        12	Imagine (Original Mix)	Bass Modulators	150.00	08A	Imagine WEB	L4H	04:29	     	12
        13	Imaginary (Original Mix)	Brennan Heart & Jonathan Mendelsohn	149.99	09A	Imaginary WEB	L4H	04:58	     	13
        14	Through The Night (Original Mix)	KELTEK	150.11	09A	Through The Night WEB	L4H	03:26	     	14
        15	Above Average (Original Mix)	D-Block & S-Te-Fan	150.00	09A	Above Average WEB	Hardstyle	04:48	     	15
        16	Before You Go (Original Mix)	MYST	154.01	09A	Prophecy WEB	L4R	04:40	     	16
        17	Zombie (Original Mix)	Ran-D	155.00	09A	Zombie WEB	L4R	05:14	     	17
        18	Encore	Wildstylez	148.00	11A	Q-Dance Hardstyle Top 40 2016-07	L4H	05:17	     	18
        19	#MyWay	Ran-D	150.00	01A	#MyWay WEB	L4R	06:30	     	19
        20	No Time To Waste (Defqon.1 Anthem 2010)	Wildstylez	148.00	11A	Fear FM Hardstyle Top 100 2010	Hardstyle Anthem	08:34	     	20
        21	Lost In Dreams (Masters Of Ceremony Remix)	Mike NRG	85.00	05A	Lost In Dreams (Q-Base Anthem: The 2010 Edition)	Hardcore Anthem	05:17	     	21
    """.trimIndent()

    val EP06 = """
        #	Track Title	Artist	BPM	Key	Time	Genre	Comments	Rating
        1	Maximum Force (defqon.1 Australia 2009 Anthem)	Zany	150.00	06A	05:01	Hardstyle Anthem	001
        2	Scrap Attack (DefQon.1 2009 Anthem) 	Headhunterz	150.11	10A	07:17	Hardstyle Anthem	002
        3	No Time To Waste (Defqon.1 Anthem 2010)	Wildstylez	148.00	11A	08:34	Hardstyle Anthem	003
        4	Unite (Official Defqon.1 Anthem 2011)	Noisecontrollers	150.00	04A	06:50	Hardstyle Anthem	004
        5	World Of Madness (Defqon.1 Anthem 2012)	Headhunterz & Wildstylez Vs. Noisecontrollers	150.00	03A	06:25	Hardstyle Anthem	005
        6	Scrap The System (Defqon 1 Australia 2013 Anthem)	Brennan Heart	150.00	04A	04:03	Hardstyle Anthem	006	*****
        7	Unleash The Beast (Official Defqon 1 Australia 2014 Anthem)	Code Black	150.00	04A	05:43	Hardstyle Anthem	007
        8	Unleash the Beast (Defqon 1 Chile Anthem 2015)	Wildstylez	148.00	02A	05:50	Hardstyle Anthem	008
        9	Dragonblood (Defqon.1 Anthem 2016)	Bass Modulators	150.00	03A	06:54	Hardstyle Anthem	009
        10	Dragonblood (Defqon.1 Australia Anthem 2016)	Audiofreq & Code Black & Toneshifterz	150.00	04A	06:12	Hardstyle Anthem	010
        11	Dragonblood (Defqon.1 Chile Anthem 2016)	Frontliner	149.91	04A	05:47	Hardstyle Anthem	011
        12	No Guts No Glory (Defqon.1 Australia Anthem 2015)	Frontliner & Dillytek feat. 360	150.00	04A	06:40	Hardstyle Anthem	012
        13	No Guts, No Glory (Defqon.1 Anthem 2015)	Ran-D feat. Skits Vicious	150.11	04A	08:04	Hardstyle Anthem	013
        14	True Rebel Freedom (Defqon 1 Australia 2012 Anthem)	Wildstylez	150.00	06A	06:44	Hardstyle Anthem	014
        15	Eye Of The Storm (Defqon.1 Australia Anthem 2017) (Pro Mix)	D-Block & S-Te-Fan	149.99	08A	05:08	Hardstyle Anthem	015
        16	Save Your Scrap for Victory (Defqon 1 Australia Anthem 2010)	Headhunterz	150.00	10B	06:15	Hardstyle Anthem	016
        17	Survival Of The Fittest (Defqon 1 Anthem 2014)	Coone	150.00	11A	06:15	Hardstyle Anthem	017
        18	Psychedelic Wasteland (Official Defqon.1 Australia Anthem 2011)	Toneshifterz	150.00	11A	05:29	Hardstyle Anthem	018
        19	Weekend Warriors (Official Defqon 1 2013 Anthem)	Frontliner	150.01	11A	05:33	Hardstyle Anthem	019	*****
        20	Victory Forever (Defqon.1 Anthem 2017)	Frequencerz	150.00	11A	05:12	Hardstyle Anthem	020
        21	Maximum Force (Defqon.1 Anthem 2018) (Pro Mix)	Project One	150.00	08A	05:50	Hardstyle Anthem	021
    """.trimIndent()

    val SPD_12_YEARS = """
        #	Track Title	Artist	BPM	Key	Time	Genre	Comments	Rating
        1	Live The Night (Original Mix)	W&W, Hardwell ft. Lil Jon	128.00	04A	04:33	Big Room	001 - 108
        2	ACDC - Thunderstruck (Shameless Bootleg)		130.00	10A	02:57	Melbourne Bounce	002 - 104
        3	Party Till We Die (feat. Andrew W.K.) [Extended Mix]	MAKJ & Timmy Trumpet	128.00	04A	03:53	Melbourne Bounce	003 - 75
        4	Bon Jovi - Its My Life (TuneSquad Bootleg) full		128.00	05A	03:48	Melbourne Bounce	004 - 195
        5	DOPEDROP - We Are The Stars (Original Mix)		128.00	04A	04:12	Melbourne Bounce	005 - 90
        6	The Hum (Original Mix)	Dimitri Vegas & Like Mike vs. Ummet Ozcan	130.00	09A	05:56	Electro House	006 - 120
        7	Bigfoot	W&W	128.00	04A	04:30	EDM	007 - 75
        8	Karate (Radio Edit)	R3hab & KSHMR	128.00	04A	02:43	EDM	008 - 142
        9	Memories (feat. Sirah)	KSHMR & Bassjackers	128.00	01A	05:05	EDM	009 - 165
        10	Freaks we will rock you (K3Leng Mashup)		128.38	09A	05:36	Big Room	010 - 222
        11	Bassjackers, D'Angello & Francis - All Aboard (Dimitri Vegas, Like Mike Edit)	Bassjackers, L3N	130.00	09A	04:17	Big Room	011 - 91
        12	Cutting Crew - Died in Your Arms (TuneSquad Bootleg) Full		128.00	10A	04:45	Melbourne Bounce	012 - 108
        13	Rave-Olution (Original Mix)	Kenneth G + AudioTwinz	128.00	11A	04:59	EDM	013 - 120
        14	Cosmic Dark vs. In The End (Hardwell UMF 2016 Mashup)	Pitchback vs. Alesso vs. David Guetta vs. Linkin Park	130.00	09A	04:22	EDM	014 - 152
        15	Final Countdown 2k16	KEVU & Luke Alive	128.00	11A	04:07	EDM	015 - 82
        16	Last Resort vs Smooth Criminal (Dimitri Vegas & Like Mike Mashup)	Papa Roach vs Ummet Ozcan & Michael Jackson	128.01	09A	03:49	Big Room	016 - 105
        17	Sweat Dreams (Ummet Ozcan Remix)	Eurythmics	128.00	12A	03:20	Big Room	017 - 112
        18	The Next Episode (Ummet Ozcan Remix)	Snoop Dogg, Ummet Ozcan	127.73	02A	03:56	Big Room	018 - 161
        19	Pop Mix	Hardscar	128.00		10:32		9019 - 632
        20	Omen	The Prodigy	140.00	10A	03:36	Electronic	020 - 113
        21	Voodoo People (Pendulum Radio Edit)	The Prodigy	87.00	01A	03:16	Drum & Bass	021 - 99
        22	Blood Sugar	Pendulum	88.00	06A	05:15	Drum & Bass	022 - 279
        23	Bring Me To Life (Pro Mix)	Da Tweekaz ft. HALIENE	150.00	09A	05:38	L4H	023 - 229
        24	Komon (Extended Mix)	Da Tweekaz	150.11	04A	04:37	L4H	024 - 124
        25	Zombie (Original Mix)	Ran-D	155.00	09A	05:14	L4R	025 - 282
    """.trimIndent()
}
