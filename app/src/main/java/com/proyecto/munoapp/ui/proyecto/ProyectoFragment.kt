package com.proyecto.munoapp.ui.proyecto

import android.app.AlertDialog
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.GridView
import android.widget.TextView
import androidx.lifecycle.ViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.proyecto.munoapp.R
import com.proyecto.munoapp.databinding.FragmentProyectoBinding
import com.proyecto.munoapp.model.ProyectoItem

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


        val gridProyecto: GridView = binding.gridProyecto




        gridProyecto.adapter = cargar(root.context,proyectoViewModel)


        var buttonAdd:FloatingActionButton = _binding!!.buttonAdd

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

    private fun cargar(context: Context, view:ProyectoViewModel):ProyectoAdapter{

        var items: ArrayList<ProyectoItem>? = view.getProyects().value as ArrayList<ProyectoItem>

        if(items!=null){
            return ProyectoAdapter(context, items)
        }

       return ProyectoAdapter(context, ArrayList())
    }

    private class ProyectoAdapter: BaseAdapter{


        var items = ArrayList<ProyectoItem>()
        var contexto: Context? = null

        constructor(context: Context, items: ArrayList<ProyectoItem>){
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
            var vista = inflador.inflate(R.layout.item_proyecto, null)


            var txtTitulo: TextView = vista.findViewById(R.id.proyect_titulo)
            var txtDescripcion: TextView = vista.findViewById(R.id.proyect_decripcion)

            txtTitulo.text=item.titulo
            txtDescripcion.text=item.decripcion

            return vista


        }


    }

}