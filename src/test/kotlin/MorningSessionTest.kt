import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.Duration
import java.time.LocalTime

class MorningSessionTest {
    @Test
    fun `should set 9 as the start time, 3 hours as max duration and 0 as the timeConsumed`() {
        val morningSession = MorningSession()

        assertThat(morningSession.startTime).isEqualTo(LocalTime.parse("09:00"))
        assertThat(morningSession.maxDurationInHours).isEqualTo(Duration.ofHours(3))
        assertThat(morningSession.timeConsumed).isEqualTo(Duration.ofMinutes(0))
    }
}