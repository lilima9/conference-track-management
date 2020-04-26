import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.Duration
import java.time.LocalTime

class TalkSchedulerTest {
    @Test
    fun `should schedule talks in morning and evening sessions`() {
        val conference = Conference()

        val talkScheduler = TalkScheduler(conference = conference, conferenceListParser = SingleTrackParser)

        talkScheduler.schedule("dummyFileName")

        assertThat(conference.tracks.size).isEqualTo(1)
        assertThat(conference.tracks[0].morningSession.talks[0].name).isEqualTo("First")
        assertThat(conference.tracks[0].morningSession.talks[1].name).isEqualTo("Second")
        assertThat(conference.tracks[0].morningSession.talks[2].name).isEqualTo("Third")
        assertThat(conference.tracks[0].morningSession.talks[3].name).isEqualTo("Fourth")
        assertThat(conference.tracks[0].lunch.startTime).isEqualTo(LocalTime.parse("12:00"))
        assertThat(conference.tracks[0].eveningSession.talks[0].name).isEqualTo("Fifth")
        assertThat(conference.tracks[0].eveningSession.talks[1].name).isEqualTo("Sixth")
        assertThat(conference.tracks[0].eveningSession.talks[2].name).isEqualTo("Seventh")
        assertThat(conference.tracks[0].eveningSession.talks[3].name).isEqualTo("Eighth")
        assertThat(conference.tracks[0].networkingEvent.startTime).isEqualTo(LocalTime.parse("16:00"))
    }

    @Test
    fun `should schedule talks in morning and evening sessions across multiple tracks`() {
        val conference = Conference()

        val talkScheduler = TalkScheduler(conference = conference, conferenceListParser = MultiTrackParser)

        talkScheduler.schedule("dummyFileName")

        assertThat(conference.tracks.size).isEqualTo(2)

        assertThat(conference.tracks[0].morningSession.talks[0].name).isEqualTo("First")
        assertThat(conference.tracks[0].morningSession.talks[1].name).isEqualTo("Second")
        assertThat(conference.tracks[0].morningSession.talks[2].name).isEqualTo("Third")
        assertThat(conference.tracks[0].morningSession.talks[3].name).isEqualTo("Fourth")
        assertThat(conference.tracks[0].lunch.startTime).isEqualTo(LocalTime.parse("12:00"))
        assertThat(conference.tracks[0].eveningSession.talks[0].name).isEqualTo("Fifth")
        assertThat(conference.tracks[0].eveningSession.talks[1].name).isEqualTo("Sixth")
        assertThat(conference.tracks[0].eveningSession.talks[2].name).isEqualTo("Seventh")
        assertThat(conference.tracks[0].eveningSession.talks[3].name).isEqualTo("Eighth")
        assertThat(conference.tracks[0].eveningSession.talks[4].name).isEqualTo("Ninth")
        assertThat(conference.tracks[0].networkingEvent.startTime).isEqualTo(LocalTime.parse("16:35"))

        assertThat(conference.tracks[1].morningSession.talks[0].name).isEqualTo("Eleventh")
        assertThat(conference.tracks[1].morningSession.talks[1].name).isEqualTo("Twelfth")
        assertThat(conference.tracks[1].morningSession.talks[2].name).isEqualTo("Thirteenth")
        assertThat(conference.tracks[1].lunch.startTime).isEqualTo(LocalTime.parse("12:00"))
        assertThat(conference.tracks[1].eveningSession.talks[0].name).isEqualTo("Fourteenth")
        assertThat(conference.tracks[1].eveningSession.talks[1].name).isEqualTo("Fifteenth")
        assertThat(conference.tracks[1].eveningSession.talks[2].name).isEqualTo("Sixteenth")
        assertThat(conference.tracks[1].eveningSession.talks[3].name).isEqualTo("Seventeenth")
        assertThat(conference.tracks[1].eveningSession.talks[4].name).isEqualTo("Eighteenth")
        assertThat(conference.tracks[1].eveningSession.talks[5].name).isEqualTo("Nineteenth")
        assertThat(conference.tracks[1].networkingEvent.startTime).isEqualTo(LocalTime.parse("16:45"))
    }

    object SingleTrackParser : TalkParser {
        override fun readFile(fileName: String): List<Talk> {
            return listOf(
                Talk("First", Duration.ofMinutes(60)),
                Talk("Second", Duration.ofMinutes(45)),
                Talk("Third", Duration.ofMinutes(30)),
                Talk("Fourth", Duration.ofMinutes(45)),
                Talk("Fifth", Duration.ofMinutes(45)),
                Talk("Sixth", Duration.ofMinutes(5)),
                Talk("Seventh", Duration.ofMinutes(60)),
                Talk("Eighth", Duration.ofMinutes(45))
            )
        }
    }

    object MultiTrackParser : TalkParser {
        override fun readFile(fileName: String): List<Talk> {
            return listOf(
                Talk("First", Duration.ofMinutes(60)),
                Talk("Second", Duration.ofMinutes(45)),
                Talk("Third", Duration.ofMinutes(30)),
                Talk("Fourth", Duration.ofMinutes(45)),
                Talk("Fifth", Duration.ofMinutes(45)),
                Talk("Sixth", Duration.ofMinutes(5)),
                Talk("Seventh", Duration.ofMinutes(60)),
                Talk("Eighth", Duration.ofMinutes(45)),
                Talk("Ninth", Duration.ofMinutes(30)),
                Talk("Tenth", Duration.ofMinutes(30)),
                Talk("Eleventh", Duration.ofMinutes(45)),
                Talk("Twelfth", Duration.ofMinutes(60)),
                Talk("Thirteenth", Duration.ofMinutes(60)),
                Talk("Fourteenth", Duration.ofMinutes(45)),
                Talk("Fifteenth", Duration.ofMinutes(30)),
                Talk("Sixteenth", Duration.ofMinutes(30)),
                Talk("Seventeenth", Duration.ofMinutes(60)),
                Talk("Eighteenth", Duration.ofMinutes(30)),
                Talk("Nineteenth", Duration.ofMinutes(30))
            )
        }
    }
}

