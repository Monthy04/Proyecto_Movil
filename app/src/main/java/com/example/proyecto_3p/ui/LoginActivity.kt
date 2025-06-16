package com.example.proyecto_3p.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.proyecto_3p.JsonStorage
import com.example.proyecto_3p.MainActivity
import com.example.proyecto_3p.Producto
import com.example.proyecto_3p.R

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        val productos = listOf(
            Producto(1, "Croquetas Premium", "Para perros adultos", "croqgit uetas", "Perros", 249.99, 10),
            Producto(2, "Juguete Gato", "Ratón de peluche", "juguete_gato", "Gatos", 99.50, 5),
            Producto(3, "Rascador", "Rascador grande", "rascador", "Gatos", 380.00, 2),
            Producto(4, "Collar", "Collar de cuero", "collar", "Perros", 150.00, 8)
        )

        JsonStorage.saveData(this,"productos.json",productos);
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
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}