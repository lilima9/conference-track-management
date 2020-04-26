class Conference {
    val tracks: MutableList<Track> = mutableListOf()

    fun addTrack(track: Track) {
        tracks.add(track)
    }
}