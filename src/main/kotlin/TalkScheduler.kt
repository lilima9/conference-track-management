import java.time.LocalTime

class TalkScheduler(
    val conference: Conference,
    private val conferenceListParser: TalkParser = ConferenceTalkListParser()
) {
    fun schedule(talkListFile: String) {
        val talks = conferenceListParser.readFile(talkListFile)
        val talksLeftToSchedule: MutableList<Talk> = talks.toMutableList()

        do {
            val morningSession = MorningSession()
            scheduleTalksFor(morningSession, talksLeftToSchedule)

            val eveningSession = EveningSession()
            scheduleTalksFor(eveningSession, talksLeftToSchedule)

            val endingTime = endingTime(eveningSession)

            val track = Track(
                morningSession = morningSession,
                lunch = Lunch(),
                eveningSession = eveningSession,
                networkingEvent = NetworkingEvent(endingTime)
            )

            conference.addTrack(track)
        } while (talksLeftToSchedule.isNotEmpty())
    }

    private fun endingTime(eveningSession: EveningSession): LocalTime {
        val minimumStartTime = LocalTime.parse("16:00")
        val eveningSessionEndTime = eveningSession.startTime.plusMinutes(eveningSession.timeConsumed.toMinutes())
        if (minimumStartTime <= eveningSessionEndTime) return eveningSessionEndTime
        return minimumStartTime
    }

    private fun scheduleTalksFor(
        session: Session,
        talksLeftToSchedule: MutableList<Talk>
    ) {
        while (talksLeftToSchedule.isNotEmpty() && session.hasSpaceFor(talksLeftToSchedule[0])) {
            session.addTalk(talksLeftToSchedule[0])
            talksLeftToSchedule.removeAt(0)
        }
    }
}
