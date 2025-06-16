package com.example.proyecto_3p.ui

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.proyecto_3p.R
import com.example.proyecto_3p.UsuarioManager
import com.example.proyecto_3p.Administrador
import com.example.proyecto_3p.RepVentas
import com.example.proyecto_3p.Usuario

class RegistroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        val etNombre = findViewById<EditText>(R.id.etNombre)
        val etPassword = findViewById<EditText>(R.id.etRegPassword)
        val etCorreo = findViewById<EditText>(R.id.etEmail)
        val etTelefono = findViewById<EditText>(R.id.etTelefono)
        val btnUnirse = findViewById<Button>(R.id.btnUnirse)
        val cbAdmin = findViewById<CheckBox>(R.id.cbAdmin)
        val rgTipoAdmin = findViewById<RadioGroup>(R.id.rgTipoAdmin)
        val rbVentas = findViewById<RadioButton>(R.id.rbVentas)
        val rbAdministracion = findViewById<RadioButton>(R.id.rbAdministracion)

        cbAdmin.setOnCheckedChangeListener { _, isChecked ->
            rgTipoAdmin.visibility = if (isChecked) View.VISIBLE else View.GONE
        }

        btnUnirse.setOnClickListener {
            val nombre = etNombre.text.toString()
            val pass = etPassword.text.toString()
            val correo = etCorreo.text.toString()
            val tel = etTelefono.text.toString()

            if (nombre.isNotEmpty() && pass.isNotEmpty() && correo.isNotEmpty() && tel.isNotEmpty()) {
                val usuario: Usuario = if (cbAdmin.isChecked) {
                    if (rbAdministracion.isChecked) {
                        Administrador().apply {
                            tipo = 1
                        }
                    } else {
                        RepVentas().apply {
                            tipo = 0
                        }
                    }
                } else {
                    Usuario()
                }

                usuario.registro = UsuarioManager.usuariosRegistrados.size + 1
                usuario.nombre = nombre
                usuario.password = pass
                usuario.correo = correo
                usuario.telefono = tel
                usuario.admin = cbAdmin.isChecked

                // Verifica si ya existe
                val existente = UsuarioManager.usuariosRegistrados.any {
                    it.nombre == usuario.nombre
                }

                if (!existente) {
                    UsuarioManager.usuariosRegistrados.add(usuario)
                    Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(this, "El usuario ya existe", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
