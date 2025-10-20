package com.erickvazquezs.practicamodulo7.data.remote.model

import com.google.gson.annotations.SerializedName

data class MovieDto(
    @SerializedName("id")
    var id: Int,
    @SerializedName("name")
    var name: String,
    @SerializedName("year")
    var year: Int,
    @SerializedName("poster")
    var poster: String? = null,
    @SerializedName("platforms")
    var platforms: List<String>,
    @SerializedName("duration")
    var duration: Float,
    @SerializedName("genre")
    var genre: String? = null,
    @SerializedName("synopsis")
    var synopsis: String? = null
)
