package com.example.proyecto_3p

import kotlin.collections.mutableListOf
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto_3p.databinding.ItemProductoBinding
import java.nio.DoubleBuffer

class CarritoAdapter(private var productos: List<Producto>) : RecyclerView.Adapter<CarritoAdapter.ProductoViewHolder>()
{
    inner class ProductoViewHolder(val binding: ItemProductoBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductoViewHolder {
        val binding = ItemProductoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductoViewHolder, position: Int)
    {
        val producto = productos[position]
        holder.binding.txtNombreProd.text = producto.nombre
        holder.binding.txtCategoriaProd.isVisible = false
        holder.binding.txtDescProd.isVisible = false
        holder.binding.txtPrecioProd.text= "$${producto.precio}"
        holder.binding.txtStock.isVisible = false
        holder.binding.txtStrockProd.isVisible = false
        holder.binding.btnAgregarCarrito.isVisible = false
    }

    override fun getItemCount(): Int = productos.size

    fun getPrecio(): Double
    {
        var precio: Double = 0.0
        for(Producto in productos)
        {
            precio += Producto.precio
        }
        return precio
    }

    fun actualizarLista(nuevaLista: List<Producto>) {
        productos = nuevaLista
        notifyDataSetChanged()
    }
}