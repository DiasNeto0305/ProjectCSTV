package com.example.orgs.model.match.opponents.opponent

import com.squareup.moshi.Json

class Opponent(
    @Json(name = "id")
    val id: Int,
    @Json(name = "image_url")
    val image_url: String,
    @Json(name = "name")
    val name: String
)
