package com.example.proyecto_3p.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.proyecto_3p.R
import com.example.proyecto_3p.Administrador
import com.example.proyecto_3p.Cliente
import com.example.proyecto_3p.JsonStorage

class RegistroActivity : AppCompatActivity()
{
    private lateinit var nombre: EditText
    private lateinit var password: EditText
    private lateinit var correo: EditText
    private lateinit var telefono: EditText
    private lateinit var btnUnirse: Button
    private lateinit var admin: CheckBox
    private lateinit var tipoAdmin:RadioGroup
    private lateinit var ventas: RadioButton
    private lateinit var administ: RadioButton

    private lateinit var clientesRegistrados: List<Cliente>
    private val nuevosClientes = mutableListOf<Cliente>()
    private lateinit var adminsRegistrados: List<Administrador>
    private val nuevosAdmins = mutableListOf<Administrador>()

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        nombre = findViewById(R.id.etNombre)
        password = findViewById(R.id.etRegPassword)
        correo = findViewById(R.id.etEmail)
        telefono = findViewById(R.id.etTelefono)
        btnUnirse = findViewById(R.id.btnUnirse)
        admin = findViewById(R.id.cbAdmin)
        tipoAdmin = findViewById(R.id.rgTipoAdmin)
        ventas = findViewById(R.id.rbVentas)
        administ = findViewById(R.id.rbAdministracion)

        clientesRegistrados = JsonStorage.loadData(this, "clientesRegistrados.json") ?: emptyList()
        adminsRegistrados = JsonStorage.loadData(this, "adminsRegistrados.json") ?: emptyList()

        for(cliente in clientesRegistrados)
        {
            nuevosClientes.add(cliente)
        }
        for(admin in adminsRegistrados)
        {
            nuevosAdmins.add(admin)
        }

        admin.setOnCheckedChangeListener { _, isChecked ->
            tipoAdmin.visibility = if (isChecked) View.VISIBLE else View.GONE
        }
    }

    fun onClick(view: View?)
    {
        when (view?.id)
        {
            R.id.btnUnirse -> registrar()
        }
    }

    private fun registrar()
    {
        val name = nombre.text
        val pass = password.text
        val email = correo.text
        val tel = telefono.text

        var usuarioRegistrado = false
        if (name.isNotEmpty() && name.isNotBlank() &&
            pass.isNotEmpty() && pass.isNotBlank() &&
            email.isNotEmpty() && email.isNotBlank() &&
            tel.isNotEmpty() && tel.isNotBlank())
        {
            if(admin.isChecked)
            {
                for(admin in adminsRegistrados)
                {
                    if(admin.nombre.equals(name.toString()))
                    {
                        usuarioRegistrado = true
                    }
                }
                if(!usuarioRegistrado)
                {
                    val nuevoAdmin = Administrador()
                    nuevoAdmin.registro = nuevosAdmins.size + 1000
                    nuevoAdmin.nombre = name.toString()
                    nuevoAdmin.password = pass.toString()
                    nuevoAdmin.correo = email.toString()
                    nuevoAdmin.telefono = tel.toString()
                    nuevoAdmin.admin = admin.isChecked
                    if(administ.isChecked)
                    {
                        nuevoAdmin.tipo = 1
                    }
                    else
                    {
                        nuevoAdmin.tipo = 0
                    }
                    nuevosAdmins.add(nuevoAdmin)
                    JsonStorage.saveData(this,"adminsRegistrados.json",nuevosAdmins)
                }

            }
            else
            {
                for(cliente in clientesRegistrados)
                {
                    if(cliente.nombre.equals(name.toString()))
                    {
                        usuarioRegistrado = true
                    }
                }
                if(!usuarioRegistrado)
                {
                    val nuevoCliente = Cliente()
                    nuevoCliente.registro = nuevosClientes.size + 1
                    nuevoCliente.nombre = name.toString()
                    nuevoCliente.password = pass.toString()
                    nuevoCliente.correo = email.toString()
                    nuevoCliente.telefono = tel.toString()
                    nuevoCliente.admin = admin.isChecked
                    nuevoCliente.compras = emptyList()
                    nuevosClientes.add(nuevoCliente)
                    JsonStorage.saveData(this,"clientesRegistrados.json",nuevosClientes)
                }
            }
            if(usuarioRegistrado)
            {
                Toast.makeText(this, "Nombre de usuario no disponible", Toast.LENGTH_SHORT).show()
            }
            else
            {
                Toast.makeText(this, "Usuario registrado", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }

        }
        else
        {
            Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
        }
    }
}
