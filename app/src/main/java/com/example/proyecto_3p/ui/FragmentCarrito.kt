package com.example.proyecto_3p.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.proyecto_3p.R

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentCarrito.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentCarrito : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_carrito, container, false)
    }
}