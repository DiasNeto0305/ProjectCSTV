package com.example.orgs.model.match

import com.example.orgs.model.match.league.League
import com.example.orgs.model.match.opponents.Opponents
import com.example.orgs.model.match.serie.Serie
import com.squareup.moshi.Json

class Match (
    @Json(name = "id")
    val id: Int,
    @Json(name = "league")
    val league: League,
    @Json(name = "serie")
    val serie: Serie,
    @Json(name = "opponents")
    val opponents: List<Opponents>,
    @Json(name = "scheduled_at")
    val scheduled_at: String
)
