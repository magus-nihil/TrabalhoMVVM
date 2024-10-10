package br.unisanta.mvvm.DAO

import br.unisanta.mvvm.model.Usuario

class UsuarioDAO {
    companion object {
        // Lista que armazena os usuários
        private val usuarios = mutableListOf<Usuario>()

        init {
            // Adiciona um usuário administrador padrão
            val usuarioAdmin = Usuario(email = "ivansolfire@gmail.com", senha = "1234")
            usuarios.add(usuarioAdmin)
        }

        // Método para adicionar um usuário
        fun adicionarUsuario(usuario: Usuario): String {
            if (usuarios.any { it.email == usuario.email }) {
                return "Usuário já cadastrado."
            }
            usuarios.add(usuario.copy(tentativas = 0, bloqueado = false))
            return "Cadastrado com sucesso."
        }

        // Método para listar todos os usuários
        fun listarUsuarios(): List<Usuario> {
            return usuarios.toList()
        }

        // Método para encontrar um usuário pelo email
        fun encontrarUsuario(email: String): Usuario? {
            return usuarios.find { it.email == email }
        }
    }
}