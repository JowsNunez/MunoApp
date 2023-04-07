package com.proyecto.munoapp.ui.tarea

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.proyecto.munoapp.R
import com.proyecto.munoapp.databinding.FragmentTareaBinding
import com.proyecto.munoapp.ui.notificacion.NotificacionViewModel

class TareaFragment : Fragment() {
    private var _binding: FragmentTareaBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val tareaViewModel =
            ViewModelProvider(this).get(TareaViewModel::class.java)

        _binding = FragmentTareaBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textTarea
        tareaViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}