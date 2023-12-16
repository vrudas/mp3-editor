package io.playlisteditor

import java.time.Duration
import java.time.LocalTime
import java.time.format.DateTimeFormatter

object SetTimeCounter {
    val tracks = listOf(
    /** +01 */   "00:25-03:12 = 000    Scatman (Extended Mix)    Da Tweekaz",
    /** +03 */   "02:01-04:28 = 000    Lose My Mind (Original Mix)    Brennan Heart & Wildstylez",
    /** +04 */   "00:25-03:47 = 000    Gave U My Love    D-Block & S-te-Fan",
    /** +05 */   "02:08-04:22 = 000    Bla Bla    D-Block & S-te-Fan",
    /** +06 */   "00:25-04:01 = 000    Addicted To The Bass (Extended Mix)    Brennan Heart x Harris & Ford",
    /** +08 */   "03:01-04:45 = 000    Toss A Coin To Your Witcher (Ft. Bram Boender) [Extended Mix]    Coone, Da Tweekaz & Hard Driver",
    /** +09 */   "00:25-02:14 = 000    Drift Away (Numa Numa Yay) (Extended Mix)    Refuzion",
    /** +10 */   "01:39-04:00 = 000    Jagermeister (Extended Mix)    Da Tweekaz",
    /** +11 */   "00:25-03:40 = 000    What Is Love (Extended)    Dee-Block & S-te-Pack",
    /** +12 */   "00:43-03:12 = 000    Muzika (Sefa Remix)    Dr. Peacock & Sefa",
    /** +13 */   "00:18-03:53 = 000    Run From Reality (feat. LePrince)    Endymion",
    /** +14 */   "01:36-03:12 = 000    Hava    Steve Aoki and Timmy Trumpet feat. Dr Phunk",
    /** +17 */   "02:16-04:44 = 000    Tequila    Da Tweekaz",
    /** +18 */   "00:25-04:46 = 000    Love On Fire (Extended Mix)    D-Block & S-te-Fan",
    /** -26 */   "01:45-04:48 = 000    Our Church (Extended Mix)    Headhunterz & Sub Zero Project",
    /** +27 */   "02:40-04:22 = 000    Darkest Hour (The Clock) (Extended Mix)    Sub Zero Project",
    /** -29 */   "01:17-02:59 = 000    Embracing The Madness (Extended Mix)    D-Sturb and Sound Rush",
    /** +30 */   "00:24-04:39 = 000    All Night (Extended Mix)    Sub Zero Project",
    /** -21 */   "00:25-03:24 = 000    Lake Of Fire    Ghost Stories",
    /** -20 */   "00:25-04:28 = 000    Harder State Of Mind    D-Block & S-Te-Fan & DJ Isaac"
//    /** -22 */   "00:25-00:00 = 000    Twilight Zone (Pro Mix)    D-Block & S-te-Fan",
//    /** -23 */   "00:25-00:00 = 000    Paper Thin (Headhunterz Remix)    Illenium Feat. Tom DeLonge, Angels & Airwaves",
//    /** -25 */   "00:25-00:00 = 000    High (Extended Mix)    Last Of Us Ft. MERYLL",
//    /** -28 */   "00:25-01:23 = 000    Arriba (Extended Mix)    Psyko Punkz and Coone and Keltek"
//    /** +07 */   "00:26-04:10 = 000    Stereo Love (Wildstylez Extended Mix)    Edward Maya & Vika Jigulina",
//        /** +19 */   "00:25-02:40 = 000    Promised Land (Original Mix)    D-Block & S-Te-Fan",
//        /** +10 */   "02:17-03:59 = 000    Imaginary (Original Mix)    Brennan Heart & Jonathan Mendelsohn",
//    /** +15 */   "00:00-02:56 = 000    Masquerade    Phuture Noize",
//    /** +16 */   "01:15-03:26 = 000    Ravers Memory    Brennan Heart & Rebourne",
//    /** -20 */   "01:55-03:50 = 000    The Ultimate Celebration (Intents Festival Anthem)    D-Block & S-Te-Fan & Frequencerz",
//    /** -24 */   "01:13-03:28 = 000    Destiny (Pro Mix)    Headhunterz",
        )

    fun secondsBetween(start: LocalTime, end: LocalTime): Long {
        return Duration.between(start, end).toSeconds()
    }

    fun String.toLocalTime(): LocalTime {
        return LocalTime.parse("00:$this", DateTimeFormatter.ofPattern("HH:mm:ss"))
    }

    fun String.extractTimes(): Pair<LocalTime, LocalTime> {
        return this.substring(0..10)
            .split("-")
            .let { (start, end) -> start.toLocalTime() to end.toLocalTime()}
    }
}
