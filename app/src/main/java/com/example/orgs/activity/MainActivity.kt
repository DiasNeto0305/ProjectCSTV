package com.example.orgs.activity
import com.example.orgs.R
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.orgs.recyclerview.adapter.ListaMatchesAdapter
import com.example.orgs.viewmodel.MatchesViewModel
import com.google.android.material.appbar.MaterialToolbar


class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val appBar = findViewById<MaterialToolbar>(R.id.topAppBar)
        appBar.title = "Partidas"
        val viewModel: MatchesViewModel = ViewModelProvider(this).get(MatchesViewModel::class.java)
        viewModel.partidasLiveData.observe(this) {
            it?.let { matches ->
                val recyclerView = findViewById<RecyclerView>(R.id.listaMatches)
                recyclerView.adapter = ListaMatchesAdapter(this, matches)
            }
        }

        viewModel.getPartidas()
    }

}