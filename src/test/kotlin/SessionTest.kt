import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.Duration

class SessionTest {
    @Test
    fun `should return true when space left for a session`() {
        val morningSession = MorningSession()

        morningSession.addTalk(Talk("First Talk", Duration.ofMinutes(30)))

        assertThat(
            morningSession.hasSpaceFor(Talk("Second Talk", Duration.ofMinutes(30)))
        ).isEqualTo(true)
    }

    @Test
    fun `should return true when a talk can exactly fit in a session`() {
        val morningSession = MorningSession()

        morningSession.addTalk(Talk("First Talk", Duration.ofMinutes(150)))

        assertThat(
            morningSession.hasSpaceFor(Talk("Second Talk", Duration.ofMinutes(30)))
        ).isEqualTo(true)
    }

    @Test
    fun `should return false when a talk cannot fit in a session`() {
        val morningSession = MorningSession()

        morningSession.addTalk(Talk("First Talk", Duration.ofMinutes(150)))

        assertThat(
            morningSession.hasSpaceFor(Talk("Second Talk", Duration.ofMinutes(45)))
        ).isEqualTo(false)
    }

    @Test
    fun `should add the talk to session talk list and increase time consumed`() {
        val morningSession = MorningSession()

        morningSession.addTalk(Talk("Talk", Duration.ofMinutes(30)))

        assertThat(morningSession.talks.size).isEqualTo(1)
        assertThat(morningSession.talks[0].name).isEqualTo("Talk")
        assertThat(morningSession.timeConsumed.toMinutes()).isEqualTo(30)
    }
}