import java.time.Duration
import java.time.LocalTime

abstract class Event {
    abstract val startTime: LocalTime
    abstract val maxDurationInHours: Duration
}

abstract class Session(
    override val startTime: LocalTime,
    override val maxDurationInHours: Duration
) : Event() {
    var timeConsumed: Duration = Duration.ofMinutes(0)
    val talks: MutableList<Talk> = mutableListOf()

    fun hasSpaceFor(talk: Talk): Boolean {
        if ((timeConsumed + talk.durationInMinutes).toMinutes() <= maxDurationInHours.toMinutes())
            return true
        return false
    }

    fun addTalk(talk: Talk) {
        talks.add(talk)
        timeConsumed += talk.durationInMinutes
    }
}

class MorningSession
    : Session(startTime = LocalTime.parse("09:00"), maxDurationInHours = Duration.ofHours(3))

class EveningSession
    : Session(startTime = LocalTime.parse("13:00"), maxDurationInHours = Duration.ofHours(4))

class Lunch(
    override val startTime: LocalTime = LocalTime.parse("12:00"),
    override val maxDurationInHours: Duration = Duration.ofHours(1)
) : Event()

class NetworkingEvent(
    override val startTime: LocalTime,
    override val maxDurationInHours: Duration = Duration.ofHours(0)
) : Event()


