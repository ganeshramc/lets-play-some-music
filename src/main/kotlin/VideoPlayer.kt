
import kotlinx.css.Color
import kotlinx.css.Display
import kotlinx.css.Position
import kotlinx.css.backgroundColor
import kotlinx.css.display
import kotlinx.css.position
import kotlinx.css.properties.deg
import kotlinx.css.properties.rotate
import kotlinx.css.properties.s
import kotlinx.css.properties.transform
import kotlinx.css.properties.transition
import kotlinx.css.px
import kotlinx.css.right
import kotlinx.css.top
import kotlinx.html.js.onClickFunction
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.ReactElement
import react.dom.h3
import styled.animation
import styled.css
import styled.styledButton
import styled.styledDiv

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
                if (props.video.songName == "Do not click this") {
                    animation (duration = 4.s, delay = 1.s) {
                        to {
                            transform {
                                rotate(360.deg)
                            }
                        }
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
