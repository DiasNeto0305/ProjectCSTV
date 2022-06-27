package com.example.orgs.model.match.opponents

import com.example.orgs.model.match.opponents.opponent.Opponent
import com.squareup.moshi.Json

class Opponents(
    @Json(name = "opponent")
    val opponent: Opponent
)
