package com.proyecto.munoapp.ui.tarea

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.ImageButton
import android.widget.TextView
import com.proyecto.munoapp.ActivityTarea
import com.proyecto.munoapp.R
import com.proyecto.munoapp.databinding.FragmentProyectoBinding
import com.proyecto.munoapp.databinding.FragmentTareaBinding
import com.proyecto.munoapp.model.TareaItem
import com.proyecto.munoapp.ui.notificacion.NotificacionViewModel
import com.proyecto.munoapp.ui.proyecto.ProyectoDialog
import com.proyecto.munoapp.ui.proyecto.ProyectoViewModel

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


        val gridProyecto: GridView = binding.gridProyecto




        gridProyecto.adapter = cargar(root.context,tareaViewModel)


        var buttonAdd: ImageButton = _binding!!.buttonAdd

        buttonAdd.setOnClickListener {
            val proyectoDialog = ProyectoDialog(root.context)
            proyectoDialog.show(parentFragmentManager,"ProyectoDialog")
        }

        return root



    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun cargar(context: Context, view: TareaViewModel):TareaAdapter{

        var items: ArrayList<TareaItem>? = view.getTareas().value as ArrayList<TareaItem>

        if(items!=null){
            return TareaAdapter(context, items)
        }

        return TareaAdapter(context, ArrayList())
    }

    private class TareaAdapter: BaseAdapter {


        var items = ArrayList<TareaItem>()
        var contexto: Context? = null

        constructor(context: Context, items: ArrayList<TareaItem>){
            this.contexto=context
            this.items=items

        }
        override fun getCount(): Int {
            return  items.size
        }

        override fun getItem(position: Int): Any {
            return  items[position]
        }

        override fun getItemId(position: Int): Long {
            return items.size.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            var item = items[position]
            var inflador = LayoutInflater.from(contexto)
            var vista = inflador.inflate(R.layout.item_tarea, null)


            var txtTitulo: TextView = vista.findViewById(R.id.tarea_titulo)
            var txtDescripcion: TextView = vista.findViewById(R.id.tarea_decripcion)
            txtTitulo.setOnClickListener {
                var intento = Intent(contexto, ActivityTarea::class.java)
                // TODO: agregar los detalles del proyecto seleccionado
                contexto!!.startActivity(intento)
            }


            txtTitulo.text=item.titulo
            txtDescripcion.text=item.decripcion

            return vista


        }


    }

}