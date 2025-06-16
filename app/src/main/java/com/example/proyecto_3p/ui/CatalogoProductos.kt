package com.example.proyecto_3p.ui

import com.example.proyecto_3p.Producto

object CatalogoProductos {
    val productos = mutableListOf<Producto>()

    init {
        // Lista inicial de productos
        productos.addAll(
            listOf(
                Producto(1, "Croquetas Premium", "Para perros adultos", "croquetas", "Perros", 249.99, 10),
                Producto(2, "Juguete Gato", "Ratón de peluche", "juguete_gato", "Gatos", 99.50, 5),
                Producto(3, "Rascador", "Rascador grande", "rascador", "Gatos", 380.00, 2),
                Producto(4, "Collar", "Collar de cuero", "collar", "Perros", 150.00, 8)
            )
        )
    }

    fun obtenerProductos(): List<Producto> {
        return productos.toList() // Devuelve copia inmutable
    }
}