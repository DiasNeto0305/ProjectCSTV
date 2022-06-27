package com.example.orgs.service

import com.example.orgs.model.match.Match
import com.example.orgs.model.player.Player
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface PandaScoreServices {
    @GET("csgo/matches/upcoming")
    fun listCSGO(
        @Query("token") token: String = "u6CWM_HVgtcXvejY5aXAZbmxt6eOVFeu3MRtm-ctCspN5ePw8tk"
    ): Call<List<Match>>

    @GET("matches/{idMatch}")
    fun matchById(
        @Path("idMatch") idMatch: Int,
        @Query("token") token: String = "u6CWM_HVgtcXvejY5aXAZbmxt6eOVFeu3MRtm-ctCspN5ePw8tk",
    ): Call<Match>

    @GET("csgo/players")
    fun playersByTeam(
        @Query("filter[team_id]") teamId: Int,
        @Query("token") token: String = "u6CWM_HVgtcXvejY5aXAZbmxt6eOVFeu3MRtm-ctCspN5ePw8tk",
    ): Call<List<Player>>
}