package ss.general.network.response

import com.google.gson.annotations.SerializedName
import general.model.Movie


class ResponseMovies {
    @SerializedName("page")
    var page : Int = 0
    @SerializedName("total_results")
    var totalResults : Int = 0
    @SerializedName("total_pages")
    var totalPages : Int = 0
    @SerializedName("results")
    var results : List<Movie> = listOf()
}