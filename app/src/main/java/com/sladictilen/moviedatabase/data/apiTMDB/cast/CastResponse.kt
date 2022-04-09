package com.sladictilen.moviedatabase.data.apiTMDB.cast

data class CastResponse(
    val cast: List<Cast>,
    val crew: List<Crew>,
    val id: Int
)