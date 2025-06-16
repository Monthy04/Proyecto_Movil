package com.example.proyecto_3p.ui

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.proyecto_3p.R
import com.example.proyecto_3p.UsuarioManager

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val etUsuario = findViewById<EditText>(R.id.editUsuario)
        val etPassword = findViewById<EditText>(R.id.editPassword)
        val btnIngresar = findViewById<Button>(R.id.btnIngresar)

        btnIngresar.setOnClickListener {
            val nombre = etUsuario.text.toString()
            val pass = etPassword.text.toString()

            val usuario = UsuarioManager.iniciarSesion(nombre, pass)

            if (usuario != null) {
                Toast.makeText(this, "Bienvenido ${usuario.nombre}", Toast.LENGTH_SHORT).show()
                // Aquí puedes pasar a otra actividad
            } else {
                Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
            }
        }
        val tvRegistrarse = findViewById<TextView>(R.id.tvRegistrate)

        tvRegistrarse.setOnClickListener {
            val intent = Intent(this, RegistroActivity::class.java)
            startActivity(intent)
        }
    }
}
