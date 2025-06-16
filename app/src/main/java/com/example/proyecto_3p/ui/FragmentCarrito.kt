package com.example.proyecto_3p.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proyecto_3p.JsonStorage
import com.example.proyecto_3p.Producto
import com.example.proyecto_3p.CarritoAdapter
import com.example.proyecto_3p.Detalle_Compra
import com.example.proyecto_3p.R
import com.example.proyecto_3p.databinding.FragmentCarritoBinding

class FragmentCarrito : Fragment()
{
    private lateinit var binding: FragmentCarritoBinding
    private lateinit var productos: List<Detalle_Compra>
    private lateinit var adapter: CarritoAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentCarritoBinding.inflate(inflater, container, false)

        productos = JsonStorage.loadData(requireContext(), "detallesCarrito.json") ?: emptyList()
        // Setup RecyclerView
        adapter = CarritoAdapter(productos)
        binding.recyclerCarrito.layoutManager = LinearLayoutManager(context)
        binding.recyclerCarrito.adapter = adapter

        binding.tvTotalItems.text = adapter.getCantidad().toString()
        val precio = adapter.getPrecio()
        binding.tvTotalPrecio.text = "$${precio}"

        binding.btnComprar.setOnClickListener{

        }

        return binding.root
    }
}