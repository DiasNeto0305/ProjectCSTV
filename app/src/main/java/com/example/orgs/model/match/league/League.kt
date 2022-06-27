package com.example.orgs.model.match.league

import com.squareup.moshi.Json

class League(
    @Json(name = "name")
    val name: String?
)