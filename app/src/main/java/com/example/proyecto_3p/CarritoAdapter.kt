package com.example.proyecto_3p

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto_3p.databinding.ItemProductoBinding

class CarritoAdapter(private var detalles: List<Detalle_Compra>) : RecyclerView.Adapter<CarritoAdapter.ProductoViewHolder>()
{
    inner class ProductoViewHolder(val binding: ItemProductoBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductoViewHolder {
        val binding = ItemProductoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductoViewHolder, position: Int)
    {
        val product = detalles[position]
        holder.binding.txtNombreProd.text = product.producto
        holder.binding.txtCategoriaProd.isVisible = false
        holder.binding.txtDescProd.isVisible = false
        holder.binding.txtPrecioProd.text= "$${product.total}"
        holder.binding.txtStock.text = "Cantidad:"
        holder.binding.txtStrockProd.text = "${product.cantidad}"
        holder.binding.btnAgregarCarrito.isVisible = false
    }

    override fun getItemCount(): Int = detalles.size

    fun getCantidad(): Int
    {
        var cantidad: Int = 0
        for(product in detalles)
        {
            cantidad += product.cantidad
        }
        return cantidad
    }

    fun getPrecio(): Double
    {
        var precio: Double = 0.0
        for(product in detalles)
        {
            precio += product.total
        }
        return precio
    }

}