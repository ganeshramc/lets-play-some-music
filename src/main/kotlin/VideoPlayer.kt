import kotlinx.css.*
import kotlinx.css.properties.*
import kotlinx.html.js.onClickFunction
import react.*
import react.dom.*
import styled.*

external interface VideoPlayerProps : RProps {
    var video: Video
    var onWatchedButtonPressed: (Video) -> Unit
    var unwatchedVideo: Boolean
}

class VideoPlayer : RComponent<VideoPlayerProps, RState>() {
    override fun RBuilder.render() {
        styledDiv {
            css {
                position = Position.absolute
                top = 10.px
                right = 10.px
                css {
                    if (props.video.songName == "Do not click this") {
                        transform {
                            rotate(360.deg)
                        }
                        transition(duration = 4.s)
                    }
                }
            }
            h3 {
                if (props.video.songName == "Do not click this")
                    +"Rick Roll"
                else
                    +"${props.video.songName} - ${props.video.singer}"
            }
            styledButton {
                css {
                    display = Display.block
                    backgroundColor = if (props.unwatchedVideo) Color.lightGreen else Color.red
                }
                attrs {
                    onClickFunction = {
                        props.onWatchedButtonPressed(props.video)
                    }
                }
                if (props.unwatchedVideo) {
                    +"Mark as watched"
                } else {
                    +"Mark as unwatched"
                }
            }
            styledDiv {
                css {
                    if (props.video.songName == "Do not click this") {
                        transform {
                            rotate(360.deg)
                        }
                        transition(duration = 4.s)
                    }
                }
            }
            ReactPlayer {
                attrs {
                    url = props.video.videoUrl
                    controls = true
                    playing = true
                }
            }
        }
    }
}

fun RBuilder.videoPlayer(handler: VideoPlayerProps.() -> Unit): ReactElement {
    return child(VideoPlayer::class) {
        this.attrs(handler)
    }
}
