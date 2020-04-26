import java.time.Duration

interface TalkParser {
    fun readFile(fileName: String): List<Talk>
}

class ConferenceTalkListParser : TalkParser {
    override fun readFile(fileName: String): List<Talk> {
        val talks = mutableListOf<Talk>()
        val file = this::class.java.getResource(fileName).readText(Charsets.UTF_8).split("\n")
        file.forEach { description ->
            talks.add(Talk(talkTitle(description), Duration.ofMinutes(talkTime(description))))
        }
        return talks
    }

    private fun talkTime(talkDescription: String): Long {
        val timing = talkDescription.substringAfterLast(" ")
        return if (timing == "lightning") 5
        else timing.substringBefore("min").toLong()
    }

    private fun talkTitle(talkDescription: String) = talkDescription.substringBeforeLast(" ")
}