package com.proyecto.munoapp.ui.mensaje

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.proyecto.munoapp.R
import com.proyecto.munoapp.databinding.FragmentMensajeBinding
import com.proyecto.munoapp.model.MensajeItem
import com.proyecto.munoapp.model.MiembroItem

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

        val gv_Mensajes = binding.gvMensajes

        gv_Mensajes.adapter =(cargarMensaje(root.context))



        return root
    }

    private fun cargarMensaje(context: Context):MensajeItemAdapter{

        val mensajes:ArrayList<MensajeItem> = ArrayList()
        mensajes.add(MensajeItem("Mensaje de prubea 1",MiembroItem("userName 1","")))
        mensajes.add(MensajeItem("Mensaje de prubea 2",MiembroItem("userName 2","")))
        mensajes.add(MensajeItem("Mensaje de prubea 3",MiembroItem("userName 3","")))


       return MensajeItemAdapter(mensajes,context)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private class MensajeItemAdapter: BaseAdapter {
        private var items: ArrayList<MensajeItem>
        private  var context:Context
        constructor( items:ArrayList<MensajeItem>, context: Context){
            this.items=items
            this.context=context
        }

        override fun getCount(): Int {
           return items.size
        }

        override fun getItem(position: Int): Any {
            return  items[position]
        }

        override fun getItemId(position: Int): Long {
           return items.size.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val inflador = LayoutInflater.from(context)
            val view: View = inflador.inflate(R.layout.item_mensajes,null)

           val txtNombreUsuario: TextView = view.findViewById(R.id.user_nombre)
            val txtMensaje: TextView = view.findViewById(R.id.message_textview)

            txtNombreUsuario.text=items[position].miembroItem.nombre
            txtMensaje.text=items[position].contenido
            return view

        }
    }



}