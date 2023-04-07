package com.proyecto.munoapp.ui.proyecto

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.proyecto.munoapp.databinding.FragmentProyectoBinding

class ProyectoFragment : Fragment() {

    private var _binding: FragmentProyectoBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val proyectoViewModel =
            ViewModelProvider(this).get(ProyectoViewModel::class.java)

        _binding = FragmentProyectoBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textProyecto
        proyectoViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}