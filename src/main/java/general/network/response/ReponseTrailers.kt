package ss.general.network.response

import com.google.gson.annotations.SerializedName
import general.model.Trailer


class ReponseTrailers {
    @SerializedName("id")
    var id: String = ""
    @SerializedName("videos")
    var video: Video? = null

    public class Video {
        @SerializedName("results")
        var results : List<Trailer> = emptyList()
    }
}