package br.unisanta.mvvm.view


import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import br.unisanta.mvvm.Adapter.UsuarioAdapter
import br.unisanta.mvvm.R
import br.unisanta.mvvm.viewmodel.LoginViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class AdminActivity : AppCompatActivity() {

    private lateinit var viewModel: LoginViewModel
    private lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)

        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        listView = findViewById(R.id.listViewUsuarios)
        val fabVoltar: FloatingActionButton = findViewById(R.id.fab_voltar)

        carregarListaUsuarios()

        fabVoltar.setOnClickListener {
            finish()
        }
    }

    private fun carregarListaUsuarios() {
        val usuarios = viewModel.listarUsuarios()


        val adapter = UsuarioAdapter(this, usuarios)
        listView.adapter = adapter

        listView.setOnItemClickListener { _, _, position, _ ->
            val usuario = usuarios[position]
            Toast.makeText(this, "Selecionado: ${usuario.email}", Toast.LENGTH_SHORT).show()
        }
    }
}

