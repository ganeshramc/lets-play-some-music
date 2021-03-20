@file:JsModule("react-player")
@file:JsNonModule

import react.*

@JsName("default")
external val ReactPlayer: RClass<ReactYouTubeProps>

external interface ReactYouTubeProps : RProps {
    var url: String
    var controls: Boolean
    var playing: Boolean
}
