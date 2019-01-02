package dmitriiserdun.gmail.com.movapp.repository.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PaginationWrapperDTO<T> {

    @SerializedName("page")
    @Expose
    var page: Int? = null
    @SerializedName("total_results")
    @Expose
    var totalResults: Int? = null
    @SerializedName("total_pages")
    @Expose
    var totalPages: Int? = null
    @SerializedName("results")
    @Expose
    var results: List<T>? = null

}
