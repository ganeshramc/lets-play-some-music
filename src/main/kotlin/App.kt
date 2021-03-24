
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.div
import react.dom.h1
import react.dom.h3
import react.dom.p
import react.setState


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
    var listOfVideos: List<Video>
}

class App : RComponent<RProps, AppState>() {
    override fun AppState.init() {
        listOfVideos = listOf(
            MusicVideo(1, "Stayin' Alive","Bee Gees",
                "https://www.youtube.com/watch?v=fNFzfwLM72c"),
            MusicVideo(2, "September", "Earth, Wind and Fire",
                "https://www.youtube.com/watch?v=Gs069dndIYk"),
            MusicVideo(3, "Old Town Road", "Lil Nas X",
                "https://www.youtube.com/watch?v=w2Ov5jzm3j8"),
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
        p {
            +"Click any video you want to watch. Just not the last one..."
        }
        div {
            h3 {
                +"Songs to watch"
            }
            musicVideoList {
                musicVideos = state.listOfVideos
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
                }
            }
        }
    }
}
