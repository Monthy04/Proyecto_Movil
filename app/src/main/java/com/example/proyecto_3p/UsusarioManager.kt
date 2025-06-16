package com.example.proyecto_3p

object UsuarioManager {
    val usuariosRegistrados = ArrayList<Usuario>()

    fun registrarUsuario(nombre: String, password: String, correo: String, telefono: String): Boolean {
        // Verifica si el nombre ya existe
        if (usuariosRegistrados.any { it.nombre == nombre }) return false

        val nuevoUsuario = Usuario()
        nuevoUsuario.registro = usuariosRegistrados.size + 1 // empieza en 1
        nuevoUsuario.nombre = nombre
        nuevoUsuario.password = password
        nuevoUsuario.correo = correo
        nuevoUsuario.telefono = telefono

        usuariosRegistrados.add(nuevoUsuario)
        return true
    }

    fun iniciarSesion(nombre: String, password: String): Usuario? {
        return usuariosRegistrados.find { it.nombre == nombre && it.password == password }
    }
}
