package com.example.proyecto_3p

import com.example.proyecto_3p.ui.CatalogoProductos

class RepVentas : Usuario() {
    var tipo: Int = 0

    fun agregarProducto(producto: Producto): Boolean {
        if (CatalogoProductos.productos.any { it.nombre == producto.nombre }) return false
        CatalogoProductos.productos.add(producto)
        return true
    }

    fun editarProducto(nombre: String, nuevoProducto: Producto): Boolean {
        val index = CatalogoProductos.productos.indexOfFirst { it.nombre == nombre }
        if (index != -1) {
            CatalogoProductos.productos[index] = nuevoProducto
            return true
        }
        return false
    }

    fun eliminarProducto(nombre: String): Boolean {
        return CatalogoProductos.productos.removeIf { it.nombre == nombre }
    }
}