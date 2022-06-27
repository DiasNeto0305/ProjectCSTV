package com.example.orgs.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.orgs.model.match.Match
import com.example.orgs.service.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PartidasViewModel : ViewModel() {

    val partidasLiveData: MutableLiveData<List<Match>> = MutableLiveData()

    fun getPartidas() {
        ApiService.service.listCSGO().enqueue(object: Callback<List<Match>> {
            override fun onResponse(call: Call<List<Match>>, response: Response<List<Match>>) {
                if (response.isSuccessful) {
                    val partidas: MutableList<Match> = mutableListOf()

                    response.body()?.let { partidasResponse ->
                        for (result in partidasResponse) {
                            val partida = Match(
                                id = result.id,
                                league = result.league,
                                serie = result.serie,
                                opponents = result.opponents,
                                scheduled_at = result.scheduled_at
                            )

                            partidas.add(partida)
                        }
                    }

                    partidasLiveData.value = partidas
                }
            }

            override fun onFailure(call: Call<List<Match>>, t: Throwable) {
                print("")
            }

        })
    }

}