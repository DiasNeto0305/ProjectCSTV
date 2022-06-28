package com.example.orgs.recyclerview.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.orgs.R
import com.example.orgs.activity.MatchDetailsActivity
import com.example.orgs.model.match.Match
import java.text.SimpleDateFormat


class ListaMatchesAdapter(
    private val context: Context,
    private val matches: List<Match>
) : RecyclerView.Adapter<ListaMatchesAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun vincula(partida: Match) {
            val timestamp = partida.scheduled_at.split("T")
            val gameDate = SimpleDateFormat("yyyy-mm-dd").parse(timestamp[0])
            val formatter = SimpleDateFormat("dd/mm")
            val gameDateFormat = formatter.format(gameDate)
            val gameTime = timestamp[1].substring(0, 5)
            val scheduledAt = itemView.findViewById<TextView>(R.id.textTime)
            scheduledAt.text = "$gameDateFormat, $gameTime"
            val nome = itemView.findViewById<TextView>(R.id.detailsText)
            val serieName = partida.serie.name ?: ""
            val leagueName = partida.league.name ?: ""
            nome.text = "$serieName | $leagueName"
            val descricao = itemView.findViewById<TextView>(R.id.team2Name)
            descricao.text = if (partida.opponents.size > 1) partida.opponents[1].opponent.name else "Indefinido"
            val valor = itemView.findViewById<TextView>(R.id.team1Name)
            valor.text = if (partida.opponents.isNotEmpty()) partida.opponents[0].opponent.name else "Indefinido"
            val teamLogo = itemView.findViewById<ImageView>(R.id.teamlogo)
            val teamLogo2 = itemView.findViewById<ImageView>(R.id.teamlogo2)
            if (partida.opponents.isNotEmpty()) {
                teamLogo.load(partida.opponents[0].opponent.image_url)
            }
            if (partida.opponents.size > 1) {
                teamLogo2.load(partida.opponents[1].opponent.image_url)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.match_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val partida = matches[position]
        holder.vincula(partida)
        if (partida.opponents.size > 1) {
            holder.itemView.setOnClickListener {
                val intent = Intent(context, MatchDetailsActivity::class.java)
                intent.putExtra("id", partida.id.toString())
                context.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int = matches.size


}
