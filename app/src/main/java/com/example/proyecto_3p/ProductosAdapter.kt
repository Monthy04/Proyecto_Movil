package com.example.proyecto_3p

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto_3p.databinding.ItemProductoBinding

class ProductosAdapter(private var productos: List<Producto>) : RecyclerView.Adapter<ProductosAdapter.ProductoViewHolder>() {

    inner class ProductoViewHolder(val binding: ItemProductoBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductoViewHolder {
        val binding = ItemProductoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductoViewHolder, position: Int) {
        val producto = productos[position]
        holder.binding.nombre.text = producto.nombre
        holder.binding.precio.text = "$${producto.precio}."
        holder.binding.desc.text = producto.desc
    }

    override fun getItemCount(): Int = productos.size

    fun actualizarLista(nuevaLista: List<Producto>) {
        productos = nuevaLista
        notifyDataSetChanged()
    }
}