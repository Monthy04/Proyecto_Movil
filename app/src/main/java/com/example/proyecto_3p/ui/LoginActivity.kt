package com.example.proyecto_3p.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.proyecto_3p.Administrador
import com.example.proyecto_3p.Cliente
import com.example.proyecto_3p.JsonStorage
import com.example.proyecto_3p.MainActivity
import com.example.proyecto_3p.Producto
import com.example.proyecto_3p.R
import com.example.proyecto_3p.Usuario
import com.example.proyecto_3p.UsuarioManager

class LoginActivity : AppCompatActivity()
{
    private lateinit var usuario: EditText
    private lateinit var password: EditText
    private lateinit var btnIngresa: Button
    private lateinit var tvRegistrarse: TextView

    private lateinit var clientesRegistrados: List<Cliente>
    private lateinit var adminsRegistrados: List<Administrador>

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        clientesRegistrados = JsonStorage.loadData(this, "clientesRegistrados.json") ?: emptyList()
        adminsRegistrados = JsonStorage.loadData(this, "adminsRegistrados.json") ?: emptyList()
        usuario = findViewById(R.id.editUsuario)
        password = findViewById(R.id.editPassword)
        btnIngresa = findViewById(R.id.btnIngresar)
        tvRegistrarse = findViewById(R.id.tvRegistrate)

        tvRegistrarse.setOnClickListener {
            val intent = Intent(this, RegistroActivity::class.java)
            startActivity(intent)
        }
    }

    fun onClick(view: View?)
    {
        when (view?.id)
        {
            R.id.btnIngresar -> ingresar();
        }
    }

    private fun ingresar()
    {
        if(usuario.text.isNotEmpty() && usuario.text.isNotBlank() &&
            password.text.isNotEmpty() && password.text.isNotBlank())
        {
            val nombre = usuario.text.toString()
            val pass = password.text.toString()
            var registrado = false
            for(cliente in clientesRegistrados)
            {
                if(cliente.nombre.equals(nombre) && cliente.password.equals(pass))
                {
                    registrado = true
                }
            }
            for(admin in adminsRegistrados)
            {
                if(admin.nombre.equals(nombre) && admin.password.equals(pass))
                {
                    registrado = true
                }
            }

            if(registrado)
            {
                Toast.makeText(this, "Bienvenido $nombre", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("user", nombre)
                startActivity(intent)
                finish() // Cierra LoginActivity para que no se pueda regresar con el botón atrás
            }
            else
            {
                Toast.makeText(this,"Usuario no registrado", Toast.LENGTH_SHORT).show()
            }
        }
        else
        {
            Toast.makeText(this,"Llenar datos", Toast.LENGTH_SHORT).show()
        }
    }
}
