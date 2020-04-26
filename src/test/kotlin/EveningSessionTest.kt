import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.Duration
import java.time.LocalTime

class EveningSessionTest {
    @Test
    fun `should set 1 as the start time, 4 hours as max duration and 0 as the timeConsumed`() {
        val morningSession = EveningSession()

        assertThat(morningSession.startTime).isEqualTo(LocalTime.parse("13:00"))
        assertThat(morningSession.maxDurationInHours).isEqualTo(Duration.ofHours(4))
        assertThat(morningSession.timeConsumed).isEqualTo(Duration.ofMinutes(0))
    }
}