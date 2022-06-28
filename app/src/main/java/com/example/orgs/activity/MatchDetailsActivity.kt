package com.example.orgs.activity

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.orgs.R
import com.example.orgs.recyclerview.adapter.ListaTeam1PlayersAdapter
import com.example.orgs.recyclerview.adapter.ListaTeam2PlayersAdapter
import com.example.orgs.viewmodel.DetailsViewModel
import com.google.android.material.appbar.MaterialToolbar
import java.text.SimpleDateFormat

class MatchDetailsActivity : AppCompatActivity(R.layout.activity_match_details) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel: DetailsViewModel = ViewModelProvider(this).get(DetailsViewModel::class.java)
        val idMatch = intent.getStringExtra("id")
        viewModel.matchDetailsLiveData.observe(this) {
            it?.let { matchResponse ->

                val serieName = matchResponse.serie.name ?: ""
                val leagueName = matchResponse.league.name ?: ""
                val appBar = findViewById<MaterialToolbar>(R.id.topAppBar)
                appBar.title = "$serieName | $leagueName"
                setSupportActionBar(appBar)
                supportActionBar?.setDisplayHomeAsUpEnabled(true)

                val team1Name = findViewById<TextView>(R.id.team1Name)
                val teamLogo = findViewById<ImageView>(R.id.teamlogo)
                if (matchResponse.opponents.isNotEmpty()) {
                    val team1 = matchResponse.opponents[0].opponent
                    team1Name.text = team1.name
                    teamLogo.load(team1.image_url)
                } else team1Name.text = getString(R.string.indefinido)

                val team2Name = findViewById<TextView>(R.id.team2Name)
                val teamLogo2 = findViewById<ImageView>(R.id.teamlogo2)
                if (matchResponse.opponents.size > 1) {
                    val team2 = matchResponse.opponents[1].opponent
                    team2Name.text = team2.name
                    teamLogo2.load(team2.image_url)
                } else team2Name.text = getString(R.string.indefinido)

                val timestamp = matchResponse.scheduled_at.split("T")
                val dateFormat = SimpleDateFormat("yyyy-mm-dd")
                val gameDate = dateFormat.parse(timestamp[0])
                val formatter = SimpleDateFormat("dd/mm")
                val gameDateFormat = formatter.format(gameDate)
                val gameTime = timestamp[1].substring(0, 5)
                val scheduledAt = findViewById<TextView>(R.id.gametime)
                scheduledAt.text = "$gameDateFormat, $gameTime"
                getPlayersTeam1(viewModel)
                val team1Id = if (matchResponse.opponents.isNotEmpty()) matchResponse.opponents[0].opponent.id else 0
                viewModel.getPlayersDetails(team1Id, "Team 1")
                getPlayersTeam2(viewModel)
                val team2Id = if (matchResponse.opponents.size > 1) matchResponse.opponents[1].opponent.id else 0
                viewModel.getPlayersDetails(team2Id, "Team 2")
            }
        }
        viewModel.getMatchDetails(Integer.parseInt(idMatch))
    }

    private fun getPlayersTeam1(
        viewModel: DetailsViewModel
    ) {
        viewModel.playersTeam1LiveData.observe(this) {
            it?.let { players ->
                val recyclerView = findViewById<RecyclerView>(R.id.listaTeam1)
                recyclerView.adapter = ListaTeam1PlayersAdapter(this, players)
            }
        }
    }

    private fun getPlayersTeam2(
        viewModel: DetailsViewModel
    ) {
        viewModel.playersTeam2LiveData.observe(this) {
            it?.let { players ->
                val recyclerView2 = findViewById<RecyclerView>(R.id.listaTeam2)
                recyclerView2.adapter = ListaTeam2PlayersAdapter(context = this, players)
            }
        }
    }
}