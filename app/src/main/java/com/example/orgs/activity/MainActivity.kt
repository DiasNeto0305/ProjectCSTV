package com.example.orgs.activity
import com.example.orgs.R
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.orgs.recyclerview.adapter.ListaProdutosAdapter
import com.example.orgs.viewmodel.PartidasViewModel
import com.google.android.material.appbar.MaterialToolbar


class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val appBar = findViewById<MaterialToolbar>(R.id.topAppBar)
        appBar.title = "Partidas"
        val viewModel: PartidasViewModel = ViewModelProvider(this).get(PartidasViewModel::class.java)
        viewModel.partidasLiveData.observe(this) {
            it?.let { partidas ->
                val recyclerView = findViewById<RecyclerView>(R.id.listaPartidas)
                recyclerView.adapter = ListaProdutosAdapter(this, partidas)
            }
        }

        viewModel.getPartidas()
    }

}