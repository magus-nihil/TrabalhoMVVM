package br.unisanta.mvvm.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import br.unisanta.mvvm.R
import br.unisanta.mvvm.model.Usuario

class UsuarioAdapter(context: Context, private val usuarios: List<Usuario>) : ArrayAdapter<Usuario>(context, 0, usuarios) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val usuario = getItem(position) ?: return convertView ?: View(context)

        // Inflar a view se não existir
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_usuario, parent, false)

        // Definindo os dados nas views
        val txtEmail = view.findViewById<TextView>(R.id.txtEmail)
        val txtStatus = view.findViewById<TextView>(R.id.txtStatus)

        txtEmail.text = usuario.email
        txtStatus.text = if (usuario.bloqueado) "Bloqueado" else "Ativo"

        return view
    }
}