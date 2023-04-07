package com.proyecto.munoapp.ui.notificacion

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.proyecto.munoapp.R
import com.proyecto.munoapp.databinding.FragmentNotificacionBinding
import com.proyecto.munoapp.ui.mensaje.MensajeViewModel

class NotificacionFragment : Fragment() {

    private var _binding: FragmentNotificacionBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val notificacionViewModel =
            ViewModelProvider(this).get(NotificacionViewModel::class.java)

        _binding = FragmentNotificacionBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textNotification
        notificacionViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}