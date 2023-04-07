package com.proyecto.munoapp.ui.mensaje

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.proyecto.munoapp.databinding.FragmentMensajeBinding

class MensajeFragment : Fragment() {

    private var _binding: FragmentMensajeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val mensajeViewModel =
            ViewModelProvider(this).get(MensajeViewModel::class.java)

        _binding = FragmentMensajeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textMensaje
        mensajeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}