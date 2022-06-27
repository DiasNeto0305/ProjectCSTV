package com.example.orgs.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.orgs.model.match.Match
import com.example.orgs.model.player.Player
import com.example.orgs.service.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailsViewModel: ViewModel() {

    val matchDetailsLiveData: MutableLiveData<Match> = MutableLiveData()
    val playersTeam1LiveData: MutableLiveData<List<Player>> = MutableLiveData()
    val playersTeam2LiveData: MutableLiveData<List<Player>> = MutableLiveData()

    fun getMatchDetails(idMatch: Int) {
        ApiService.service.matchById(idMatch = idMatch).enqueue(object: Callback<Match> {
            override fun onResponse(call: Call<Match>, response: Response<Match>) {
                if (response.isSuccessful) {
                    response.body()?.let { matchResponse ->
                            val partida = Match(
                                id = matchResponse.id,
                                league = matchResponse.league,
                                serie = matchResponse.serie,
                                opponents = matchResponse.opponents,
                                scheduled_at = matchResponse.scheduled_at
                            )
                            matchDetailsLiveData.value = partida
                    }
                }
            }

            override fun onFailure(call: Call<Match>, t: Throwable) {
                print("")
            }

        })
    }

    fun getPlayersDetails(teamId: Int, opponent: String) {
        ApiService.service.playersByTeam(teamId = teamId).enqueue(object: Callback<List<Player>> {
            override fun onResponse(call: Call<List<Player>>, response: Response<List<Player>>) {
                if (response.isSuccessful) {
                    val players: MutableList<Player> = mutableListOf()

                    response.body()?.let { playersResponse ->
                        for (result in playersResponse) {
                            val player = Player(
                                image_url = result.image_url,
                                name = result.name
                            )
                            players.add(player)
                        }
                    }
                    if (opponent == "Team 1") playersTeam1LiveData.value = players
                    if (opponent == "Team 2") playersTeam2LiveData.value = players
                }
            }

            override fun onFailure(call: Call<List<Player>>, t: Throwable) {
                print("")
            }

        })
    }

}