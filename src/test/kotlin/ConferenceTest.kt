import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalTime

class ConferenceTest {
    @Test
    fun `should add track to the conference`() {
        val track = Track(
            morningSession = MorningSession(),
            eveningSession = EveningSession(),
            lunch = Lunch(),
            networkingEvent = NetworkingEvent(LocalTime.now())
        )
        val conference = Conference()

        conference.addTrack(track)

        assertThat(conference.tracks.size).isEqualTo(1)
        assertThat(conference.tracks[0]).isEqualTo(track)
    }
}
