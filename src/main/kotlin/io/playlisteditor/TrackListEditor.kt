package io.playlisteditor

fun main(args: Array<String>) {
    val trackListEditor = TrackListEditor()
    println(trackListEditor.parseTrackList(TrackLists.EP01))
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
    fun toTrackListString(): String = "${numberToString()} $artist - $title"

    private fun numberToString() = if (number in 0..9) "0$number" else number.toString()
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
}