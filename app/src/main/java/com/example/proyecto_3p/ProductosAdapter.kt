package com.example.proyecto_3p

import kotlin.collections.mutableListOf
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto_3p.databinding.ItemProductoBinding

class ProductosAdapter(private var productos: List<Producto>) : RecyclerView.Adapter<ProductosAdapter.ProductoViewHolder>()
{
    private val productosCarrito = mutableListOf<Producto>()

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
        holder.binding.txtCategoriaProd.text = producto.categoria
        holder.binding.txtDescProd.text = producto.desc
        holder.binding.txtPrecioProd.text= "${producto.precio}"
        holder.binding.txtStrockProd.text= "$${producto.disponibilidad}"

        holder.binding.btnAgregarCarrito.setOnClickListener{
            Toast.makeText(holder.itemView.context,"Producto Agregado", Toast.LENGTH_SHORT).show()

            productosCarrito.add(productos[position])
            JsonStorage.saveData(holder.itemView.context,"productos.json",productosCarrito)
        }
    }

    override fun getItemCount(): Int = productos.size

    fun actualizarLista(nuevaLista: List<Producto>) {
        productos = nuevaLista
        notifyDataSetChanged()
    }
}