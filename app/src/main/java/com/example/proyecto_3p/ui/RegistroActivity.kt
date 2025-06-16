package com.example.proyecto_3p.ui

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.proyecto_3p.R
import com.example.proyecto_3p.UsuarioManager

class RegistroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        val etNombre = findViewById<EditText>(R.id.etNombre)
        val etPassword = findViewById<EditText>(R.id.etRegPassword)
        val etCorreo = findViewById<EditText>(R.id.etEmail)
        val etTelefono = findViewById<EditText>(R.id.etTelefono)
        val btnUnirse = findViewById<Button>(R.id.btnUnirse)

        btnUnirse.setOnClickListener {
            val nombre = etNombre.text.toString()
            val pass = etPassword.text.toString()
            val correo = etCorreo.text.toString()
            val tel = etTelefono.text.toString()

            if (nombre.isNotEmpty() && pass.isNotEmpty() && correo.isNotEmpty() && tel.isNotEmpty()) {
                val exito = UsuarioManager.registrarUsuario(nombre, pass, correo, tel)
                if (exito) {
                    Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()
                    finish() // regresa a pantalla de login
                } else {
                    Toast.makeText(this, "El usuario ya existe", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
