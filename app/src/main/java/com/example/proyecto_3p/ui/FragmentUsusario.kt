package com.example.proyecto_3p.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

import com.example.proyecto_3p.Administrador
import com.example.proyecto_3p.R
import com.example.proyecto_3p.Usuario
import com.example.proyecto_3p.UsuarioManager

class FragmentUsusario : Fragment() {

    private lateinit var usuario: Usuario // puede ser Administrador o RepVentas también

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_usuario, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Supongamos que recibiste al usuario por argumentos, sesión o bundle
        usuario = UsuarioManager.usuarioActual ?: return

        view.findViewById<TextView>(R.id.tvNombreUsuario).text = usuario.nombre
        view.findViewById<TextView>(R.id.tvRegistro).text = "Registro: ${usuario.registro}"
        view.findViewById<TextView>(R.id.tvCorreo).text = "Correo: ${usuario.correo}"
        view.findViewById<TextView>(R.id.tvTelefono).text = "Teléfono: ${usuario.telefono}"

        val btnCambiarContrasena = view.findViewById<Button>(R.id.btnCambiarContrasena)
        val btnCerrarSesion = view.findViewById<Button>(R.id.btnCerrarSesion)

        // Botones de admin y ventas
        val btnAgregar = view.findViewById<Button>(R.id.btnAgregarProducto)
        val btnEditar = view.findViewById<Button>(R.id.btnEditarProducto)
        val btnEliminar = view.findViewById<Button>(R.id.btnEliminarProducto)
        val btnEditarUsuario = view.findViewById<Button>(R.id.btnEditarUsuario)
        val btnEliminarUsuario = view.findViewById<Button>(R.id.btnEliminarUsuario)

        // Mostrar según tipo
        if (usuario is Administrador) {
            btnAgregar.visibility = View.VISIBLE
            btnEditar.visibility = View.VISIBLE
            btnEliminar.visibility = View.VISIBLE

            if ((usuario as Administrador).tipo == 1) {
                btnEditarUsuario.visibility = View.VISIBLE
                btnEliminarUsuario.visibility = View.VISIBLE
            }
        }

        btnCerrarSesion.setOnClickListener {
            UsuarioManager.usuarioActual = null
            val intent = Intent(requireContext(), LoginActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }

        btnCambiarContrasena.setOnClickListener {
            Toast.makeText(requireContext(), "Funcionalidad pendiente", Toast.LENGTH_SHORT).show()
        }
    }
}
