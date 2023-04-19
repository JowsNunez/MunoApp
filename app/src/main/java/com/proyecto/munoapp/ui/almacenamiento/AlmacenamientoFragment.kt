package com.proyecto.munoapp.ui.almacenamiento

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.proyecto.munoapp.ActivityProyecto
import com.proyecto.munoapp.R
import com.proyecto.munoapp.databinding.FragmentAlmacenamientoBinding
import com.proyecto.munoapp.model.ArchivoItem
import com.proyecto.munoapp.model.ProyectoItem


class AlmacenamientoFragment : Fragment() {


    private var _binding: FragmentAlmacenamientoBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val almacenamientoViewModel =
            ViewModelProvider(this).get(AlmacenamientoViewModel::class.java)

        _binding = FragmentAlmacenamientoBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val gridView = binding.gridAlmacenamiento
        val btn_add=binding.buttonAdd

        btn_add.setOnClickListener {
            var archivoDialog=ArchivoDialog()
            archivoDialog.show(parentFragmentManager,"ArchivoDialog")
        }

        gridView.adapter=cargarArchivos(root.context)

        return root
    }

    private fun cargarArchivos(context: Context):ArchivoAdapter {
        var list: ArrayList<ArchivoItem> = ArrayList()

        list.add(ArchivoItem("Miarchivo.rar", 89.9.toFloat(), R.drawable.formato_rar_))
        list.add(ArchivoItem("Miimagen.png", 19.9.toFloat(), R.drawable.formato_imagen_))
        list.add(ArchivoItem("Miarchivo.ai", 140.toFloat(), R.drawable.formato_ai_))
        list.add(ArchivoItem("Miarchivo.ps", 5.5.toFloat(), R.drawable.formato_ps_))

        return ArchivoAdapter(context,list)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private class ArchivoAdapter : BaseAdapter {


        var items = ArrayList<ArchivoItem>()
        var contexto: Context? = null

        constructor(context: Context, items: ArrayList<ArchivoItem>) {
            this.contexto = context
            this.items = items

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
            var item = items[position]
            var inflador = LayoutInflater.from(contexto)
            var vista = inflador.inflate(R.layout.item_almacenamiento, null)


            var txtTitulo: TextView = vista.findViewById(R.id.titulo)
            var txtDescripcion: TextView = vista.findViewById(R.id.peso)
            var img: ImageView = vista.findViewById(R.id.item_imagen)



            txtTitulo.text = item.titulo
            txtDescripcion.text = "${item.peso} Mb"
            img.setImageResource(item.imagen)

            return vista


        }


    }
}