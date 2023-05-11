package com.proyecto.munoapp.ui.notificacion

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.BaseAdapter
import android.widget.TextView
import com.proyecto.munoapp.R
import com.proyecto.munoapp.databinding.FragmentNotificacionBinding
import com.proyecto.munoapp.model.NotificacionItem
import com.proyecto.munoapp.ui.mensaje.MensajeViewModel
import org.w3c.dom.Text

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

        val gv_notificaciones =  binding.gvNotificacion

        gv_notificaciones.adapter=cargarNotificaciones(root.context)


        return root
    }


    private fun cargarNotificaciones(context:Context): NotificacionItemAdapter{

        val notificaciones: ArrayList<NotificacionItem> = ArrayList()


        notificaciones.add(NotificacionItem("Roberto","A concluido tarea"))
        notificaciones.add(NotificacionItem("Jose","Te menciono en proyecto EcoLearn"))
        notificaciones.add(NotificacionItem("Laura","A creado una alerta"))


        return NotificacionItemAdapter(notificaciones,context)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private class NotificacionItemAdapter: BaseAdapter{
        var items: ArrayList<NotificacionItem>
        var context: Context
        constructor(items:ArrayList<NotificacionItem>, context:Context){
            this.items=items
            this.context=context

        }

        override fun getCount(): Int {
            return items.size
        }

        override fun getItem(position: Int): Any {
            return items[position]

        }

        override fun getItemId(position: Int): Long {
            return items.size.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

            val inflater = LayoutInflater.from(context)
            val view = inflater.inflate(R.layout.item_notify,null)

            val txtNotificacion: TextView = view.findViewById(R.id.message_notificacion)
            val txtNombre:TextView =view.findViewById(R.id.miembro_nombre)

            txtNotificacion.text = items[position].contenido
            txtNombre.text = items[position].nombre



            return view


        }
    }

}