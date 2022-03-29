package com.sladictilen.moviedatabase.data.api.cast

data class CastResponse(
    val cast: List<Cast>,
    val crew: List<Crew>,
    val id: Int
)