import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.Duration
import java.time.LocalTime

class LunchTest {
    @Test
    fun `should set 1 as the start time, 4 hours as max duration and 0 as the timeConsumed`() {
        val morningSession = Lunch()

        assertThat(morningSession.startTime).isEqualTo(LocalTime.parse("12:00"))
        assertThat(morningSession.maxDurationInHours).isEqualTo(Duration.ofHours(1))
    }
}