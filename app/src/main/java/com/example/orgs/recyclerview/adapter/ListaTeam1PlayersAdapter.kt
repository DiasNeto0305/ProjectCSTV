package com.example.orgs.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.orgs.R
import com.example.orgs.model.player.Player

class ListaTeam1PlayersAdapter(
    private val context: Context,
    private val players: List<Player>
) : RecyclerView.Adapter<ListaTeam1PlayersAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun vincula(player: Player) {
            val nickname = itemView.findViewById<TextView>(R.id.team1PlayersNickname)
            nickname.text = player.name
            val fotoPlayer = itemView.findViewById<ImageView>(R.id.fotoPlayer)
            if (player.image_url != null) {
                fotoPlayer.load(player.image_url)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val itemView = inflater.inflate(R.layout.team1_player_card, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val player = players[position]
        holder.vincula(player)
    }

    override fun getItemCount(): Int = players.size

}