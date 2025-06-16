package com.example.proyecto_3p.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

import com.example.proyecto_3p.Administrador
import com.example.proyecto_3p.R
import com.example.proyecto_3p.RepVentas
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
        when (usuario) {
            is Administrador -> {
                btnAgregar.visibility = View.VISIBLE
                btnEditar.visibility = View.VISIBLE
                btnEliminar.visibility = View.VISIBLE
                btnEditarUsuario.visibility = View.VISIBLE
                btnEliminarUsuario.visibility = View.VISIBLE
            }
            is RepVentas -> {
                btnAgregar.visibility = View.VISIBLE
                btnEditar.visibility = View.VISIBLE
                btnEliminar.visibility = View.VISIBLE
            }
            // Cliente no necesita botones adicionales
        }

        btnCerrarSesion.setOnClickListener {
            UsuarioManager.usuarioActual = null
            val intent = Intent(requireContext(), LoginActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }

        btnCambiarContrasena.setOnClickListener {
            val dialogView = layoutInflater.inflate(R.layout.dialog_cambiar_contrasena, null)
            val etNuevaContrasena = dialogView.findViewById<EditText>(R.id.etNuevaContrasena)
            val btnConfirmar = dialogView.findViewById<Button>(R.id.btnConfirmarCambio)

            val dialog = AlertDialog.Builder(requireContext())
                .setView(dialogView)
                .create()

            btnConfirmar.setOnClickListener {
                val nuevaContrasena = etNuevaContrasena.text.toString()
                val usuario = UsuarioManager.usuarioActual
                if (usuario?.cambiarContrasena(nuevaContrasena) == true) {
                    Toast.makeText(requireContext(), "Contraseña actualizada", Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                } else {
                    Toast.makeText(requireContext(), "Introduce una contraseña válida", Toast.LENGTH_SHORT).show()
                }
            }

            dialog.show()
        }
    }
}
