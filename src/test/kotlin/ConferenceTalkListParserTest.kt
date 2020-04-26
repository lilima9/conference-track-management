import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.Duration

class ConferenceTalkListParserTest {
    @Test
    fun `should read file and create a list of talks with duration`() {
        val inputParser = ConferenceTalkListParser()

        val talkList = inputParser.readFile("talkList")

        assertThat(talkList[0]).isEqualTo(Talk("Writing Fast Tests Against Enterprise Rails", Duration.ofMinutes(60)))
        assertThat(talkList[1]).isEqualTo(Talk("Overdoing it in Python", Duration.ofMinutes(45)))
        assertThat(talkList[2]).isEqualTo(Talk("Lua for the Masses", Duration.ofMinutes(30)))
    }

    @Test
    fun `should read file and parse lightning talk entries`() {
        val inputParser = ConferenceTalkListParser()

        val talkList = inputParser.readFile("talkList")

        assertThat(talkList[3]).isEqualTo(Talk("Rails for Python Developers", Duration.ofMinutes(5)))
    }
}