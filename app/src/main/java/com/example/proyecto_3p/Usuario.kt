package com.example.proyecto_3p

open class Usuario {
    var registro: Int = 0
    var nombre: String = ""
    var password: String = ""
    var correo: String = ""
    var telefono: String = ""
    var admin: Boolean=false

    open fun cambiarContrasena(nueva: String): Boolean {
        return if (nueva.isNotBlank()) {
            password = nueva
            true
        } else {
            false
        }
    }
}