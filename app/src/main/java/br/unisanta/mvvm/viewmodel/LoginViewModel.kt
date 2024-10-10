package br.unisanta.mvvm.viewmodel

import androidx.lifecycle.ViewModel
import br.unisanta.mvvm.DAO.UsuarioDAO
import br.unisanta.mvvm.model.Usuario

class LoginViewModel : ViewModel() {

    fun entrar(user: Usuario): String {
        val usuarioEncontrado = UsuarioDAO.encontrarUsuario(user.email)

        if (usuarioEncontrado == null) {
            return "Usuário não encontrado."
        }

        if (usuarioEncontrado.bloqueado) {
            return "Usuário está bloqueado."
        }

        return if (usuarioEncontrado.senha == user.senha) {
            usuarioEncontrado.tentativas = 0 // Resetar tentativas ao logar
            "Login realizado com sucesso."
        } else {
            usuarioEncontrado.tentativas++
            if (usuarioEncontrado.tentativas >= 3) {
                usuarioEncontrado.bloqueado = true
                "Usuário bloqueado após 3 tentativas."
            } else {
                "Login inválido. Tentativas: ${usuarioEncontrado.tentativas}."
            }
        }
    }

    fun cadastrar(user: Usuario): String {
        return UsuarioDAO.adicionarUsuario(user) // Usando o DAO para cadastrar
    }

    fun listarUsuarios(): List<Usuario> {
        return UsuarioDAO.listarUsuarios() // Usando o DAO para listar
    }
}
