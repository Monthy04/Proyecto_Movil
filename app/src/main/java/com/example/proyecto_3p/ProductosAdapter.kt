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
    private val detallesCarrito = mutableListOf<Detalle_Compra>()

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

        val categoria = "Categoria: ${producto.categoria}"
        holder.binding.txtCategoriaProd.text = categoria

        holder.binding.txtDescProd.text = producto.desc

        holder.binding.txtPrecioProd.text= "$${producto.precio}"

        holder.binding.txtStrockProd.text= "${producto.disponibilidad}."

        holder.binding.btnAgregarCarrito.setOnClickListener{

            if(productosCarrito.contains(productos[position]))
            {
                for(info in detallesCarrito)
                {
                    if (info.producto.equals(productos[position].nombre))
                    {
                        info.cantidad++
                        info.total = (info.total * info.cantidad)
                    }
                }
            }
            else
            {
                productosCarrito.add(productos[position])
                var detalles: Detalle_Compra =  Detalle_Compra()
                detalles.producto = productos[position].nombre
                detalles.cantidad = 1
                detalles.total = productos[position].precio
                detallesCarrito.add(detalles)
            }
            Toast.makeText(holder.itemView.context,"Producto Agregado", Toast.LENGTH_SHORT).show()
            JsonStorage.saveData(holder.itemView.context,"carrito.json",productosCarrito)
            JsonStorage.saveData(holder.itemView.context,"detallesCarrito.json",detallesCarrito)
        }
    }

    override fun getItemCount(): Int = productos.size

    fun actualizarLista(nuevaLista: List<Producto>) {
        productos = nuevaLista
        notifyDataSetChanged()
    }
}