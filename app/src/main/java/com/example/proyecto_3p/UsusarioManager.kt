package com.example.proyecto_3p

object UsuarioManager {
    val usuariosRegistrados = ArrayList<Usuario>()
    var usuarioActual: Usuario? = null // <--- Aquí lo agregamos

    fun registrarUsuario(nombre: String, password: String, correo: String, telefono: String): Boolean {
        if (usuariosRegistrados.any { it.nombre == nombre }) return false

        val nuevoUsuario = Usuario()
        nuevoUsuario.registro = usuariosRegistrados.size + 1
        nuevoUsuario.nombre = nombre
        nuevoUsuario.password = password
        nuevoUsuario.correo = correo
        nuevoUsuario.telefono = telefono

        usuariosRegistrados.add(nuevoUsuario)
        return true
    }

    fun iniciarSesion(nombre: String, password: String): Usuario? {
        val usuario = usuariosRegistrados.find { it.nombre == nombre && it.password == password }
        usuarioActual = usuario // <--- Aquí se guarda el usuario que inició sesión
        return usuario
    }
}

