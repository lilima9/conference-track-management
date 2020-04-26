fun main() {
    val conference = Conference()
    val talkScheduler = TalkScheduler(conference)
    talkScheduler.schedule("talkList")
}