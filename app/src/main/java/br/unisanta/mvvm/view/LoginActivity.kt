package br.unisanta.mvvm.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.unisanta.mvvm.databinding.ActivityLoginBinding
import br.unisanta.mvvm.model.Usuario
import br.unisanta.mvvm.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val viewModel = LoginViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnEntrar.setOnClickListener {
            val email = binding.edtLogin.text.toString().trim()
            val senha = binding.edtSenha.text.toString().trim()
            val user = Usuario(email, senha)

            // Tenta entrar
            val resp = viewModel.entrar(user)
            Toast.makeText(this, resp, Toast.LENGTH_SHORT).show()

            if (resp == "Login realizado com sucesso.") {
                // Verifica se o usuário é admin
                val usuarios = viewModel.listarUsuarios()
                val isAdmin = usuarios.any { it.email == user.email && it.senha == user.senha }

                if (isAdmin) {
                    startActivity(Intent(this, AdminActivity::class.java))
                } else {
                    Toast.makeText(this, "Usuário logado com sucesso, mas não é administrador.", Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.btnSalvar.setOnClickListener {
            val email = binding.edtLogin.text.toString().trim()
            val senha = binding.edtSenha.text.toString().trim()
            val user = Usuario(email, senha)

            if (email.isNotBlank() && senha.isNotBlank()) {
                val resp = viewModel.cadastrar(user)
                Toast.makeText(this, resp, Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show()
            }
        }

        // Funcionalidade para listar usuários
        binding.btnListar.setOnClickListener {
            val usuarios = viewModel.listarUsuarios()
            val usuariosStr = usuarios.joinToString("\n") { "${it.email} - Bloqueado: ${it.bloqueado}" }

            Toast.makeText(this,
                if (usuariosStr.isNotEmpty()) usuariosStr else "Nenhum usuário cadastrado.",
                Toast.LENGTH_LONG).show()
        }
    }
}

