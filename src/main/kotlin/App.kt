import kotlinx.css.*
import react.*
import react.dom.div
import react.dom.h1
import react.dom.h3
import react.dom.img
import styled.css
import styled.styledDiv


external interface Video {
    val id: Int
    val songName: String
    val singer: String
    val videoUrl: String
}

data class MusicVideo(
    override val id: Int,
    override val songName: String,
    override val singer: String,
    override val videoUrl: String
) : Video

external interface AppState : RState {
    var currentSong: Video?
    var watchedSongs: List<Video>
    var unwatchedSongs: List<Video>
}

class App : RComponent<RProps, AppState>() {
    override fun AppState.init() {
        unwatchedSongs = listOf(
            MusicVideo(1, "Stayin' Alive","Bee Gees",
                "https://www.youtube.com/watch?v=fNFzfwLM72c"),
            MusicVideo(2, "September", "Earth, Wind and Fire",
                "https://www.youtube.com/watch?v=Gs069dndIYk"),
            MusicVideo(3, "Old Town Road", "Lil Nas X",
                "https://www.youtube.com/watch?v=w2Ov5jzm3j8")
        )
        watchedSongs = listOf(
            MusicVideo(4, "Forever", "Chris",
                "https://www.youtube.com/watch?v=5sMKX22BHeE"),
            MusicVideo(5, "Do not click this", "Seriously",
                "https://www.youtube.com/watch?v=dQw4w9WgXcQ")
        )
    }

    override fun RBuilder.render() {
        h1 {
            +"Great songs to watch!"
        }
        div {
            h3 {
                +"Songs to watch"
            }
            musicVideoList {
                musicVideos = state.unwatchedSongs
                selectedSong = state.currentSong
                onSelectSong = { video ->
                    setState {
                        currentSong = video
                    }
                }
            }
            h3 {
                +"Songs watched"
            }
            musicVideoList {
                musicVideos = state.watchedSongs
                selectedSong = state.currentSong
                onSelectSong = { video ->
                    setState {
                        currentSong = video
                    }
                }
            }
            state.currentSong?.let { currentVideo ->
                videoPlayer {
                    video = currentVideo
                    unwatchedVideo = currentVideo in state.unwatchedSongs
                    onWatchedButtonPressed = {
                        if (video in state.unwatchedSongs) {
                            setState {
                                unwatchedSongs -= video
                                watchedSongs += video
                            }
                        } else {
                            setState {
                                watchedSongs -= video
                                unwatchedSongs += video
                            }
                        }
                    }
                }
            }
        }
    }
}