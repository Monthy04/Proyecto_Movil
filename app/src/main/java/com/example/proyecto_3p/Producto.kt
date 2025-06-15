package com.example.proyecto_3p

data class Producto(
    var id: Int,
    var nombre: String,
    var desc: String,
    var imagen: String,
    var categoria: String,
    var precio: Double,
    var disponibilidad: Int
)