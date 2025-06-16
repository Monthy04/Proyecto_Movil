package com.example.proyecto_3p

import android.R
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proyecto_3p.databinding.FragmentCatalogoBinding
import com.example.proyecto_3p.ProductosAdapter
import com.example.proyecto_3p.ui.CatalogoProductos


class FragmentCatalogo : Fragment() {
    private lateinit var binding: FragmentCatalogoBinding
    private lateinit var adapter: ProductosAdapter

    private val categorias = listOf("Todos", "Perros", "Gatos")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentCatalogoBinding.inflate(inflater, container, false)

        // Usamos la lista compartida del repositorio
        adapter = ProductosAdapter(CatalogoProductos.obtenerProductos())
        binding.recyclerViewProductos.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewProductos.adapter = adapter

        // Setup categoría dropdown
        val categoriaAdapter = ArrayAdapter(requireContext(), R.layout.simple_dropdown_item_1line, categorias)
        binding.categoriaFilter.setAdapter(categoriaAdapter)

        // Eventos de filtrado
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?) = false
            override fun onQueryTextChange(newText: String?): Boolean {
                filtrarProductos()
                return true
            }
        })

        binding.categoriaFilter.setOnClickListener {
            binding.categoriaFilter.showDropDown()
        }

        binding.categoriaFilter.setOnItemClickListener { _, _, _, _ ->
            filtrarProductos()
        }

        return binding.root
    }

    private fun filtrarProductos() {
        val texto = binding.searchView.query.toString().lowercase()
        val categoriaSeleccionada = binding.categoriaFilter.text.toString()

        val filtrados = CatalogoProductos.obtenerProductos().filter {
            (categoriaSeleccionada == "Todos" || it.categoria == categoriaSeleccionada) &&
                    it.nombre.lowercase().contains(texto)
        }

        adapter.actualizarLista(filtrados)
    }
}