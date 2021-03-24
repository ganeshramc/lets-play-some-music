
import kotlinx.html.js.onClickFunction
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.ReactElement
import react.dom.b
import react.dom.p

external interface MusicVideoListProps: RProps {
    var musicVideos: List<Video>
    var selectedSong: Video?
    var onSelectSong: (Video) -> Unit
}

fun RBuilder.musicVideoList(handler: MusicVideoListProps.() -> Unit): ReactElement {
    return child(MusicVideoList::class) {
        this.attrs(handler)
    }
}

class MusicVideoList: RComponent<MusicVideoListProps, RState>() {
    override fun RBuilder.render() {
        for (video in props.musicVideos) {
            p {
                attrs {
                    onClickFunction = {
                        props.onSelectSong(video)
                    }
                }
                if (video == props.selectedSong) {
                    +"▶ "
                    b {
                        +"${video.songName} - ${video.singer}"
                    }
                }
                else {
                    +"${video.songName} - ${video.singer}"
                }
            }
        }
    }
}
