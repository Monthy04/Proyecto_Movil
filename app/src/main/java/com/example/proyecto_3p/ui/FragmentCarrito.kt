package com.example.proyecto_3p.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proyecto_3p.JsonStorage
import com.example.proyecto_3p.CarritoAdapter
import com.example.proyecto_3p.Compra
import com.example.proyecto_3p.Detalle_Compra
import com.example.proyecto_3p.databinding.FragmentCarritoBinding
import java.time.LocalDate

class FragmentCarrito : Fragment()
{
    private lateinit var binding: FragmentCarritoBinding
    private lateinit var productos: List<Detalle_Compra>
    private lateinit var adapter: CarritoAdapter
    private lateinit var compras: List<Compra>
    private val comprasNew = mutableListOf<Compra>()
    private var precio: Double = 0.0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentCarritoBinding.inflate(inflater, container, false)

        productos = JsonStorage.loadData(requireContext(), "detallesCarrito.json") ?: emptyList()
        // Setup RecyclerView
        adapter = CarritoAdapter(productos)
        binding.recyclerCarrito.layoutManager = LinearLayoutManager(context)
        binding.recyclerCarrito.adapter = adapter

        binding.tvTotalItems.text = adapter.getCantidad().toString()
        precio = adapter.getPrecio()
        binding.tvTotalPrecio.text = "$${precio}"

        binding.btnComprar.setOnClickListener{
            realizarCompra()
        }

        return binding.root
    }

    private fun realizarCompra()
    {
        val fecha = LocalDate.now()
        val usuario = "xd"
        val comprasUser = "${usuario}Compras.json"
        compras = JsonStorage.loadData(requireContext(), "comprasUser") ?: emptyList()
        for(compraRegistrada in compras)
        {
            comprasNew.add(compraRegistrada)
        }

        val nuevaCompra = Compra()
        val folio = compras.size
        nuevaCompra.folio = folio
        nuevaCompra.usuario = usuario
        nuevaCompra.fecha = fecha.toString()
        nuevaCompra.detalles = productos
        nuevaCompra.precio_total = precio

        comprasNew.add(nuevaCompra)
        JsonStorage.saveData(requireContext(),comprasUser,comprasNew)
    }
}