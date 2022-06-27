package com.example.orgs.model.player

import com.squareup.moshi.Json

class Player(
    @Json(name = "image_url")
    val image_url: String?,
    @Json(name = "name")
    val name: String,
)