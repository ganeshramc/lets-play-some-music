import kotlinx.browser.window
import kotlinx.html.js.onClickFunction
import react.*
import react.dom.*

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
                }
                +"${video.songName} - ${video.singer}"
            }
        }
    }
}
