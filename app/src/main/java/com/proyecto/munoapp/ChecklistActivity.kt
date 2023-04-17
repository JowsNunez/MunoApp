package com.proyecto.munoapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckBox
import android.widget.GridView
import android.widget.TextView
import com.proyecto.munoapp.databinding.ActivityChecklistBinding
import com.proyecto.munoapp.model.PendienteItem
import com.proyecto.munoapp.model.ProyectoItem

class ChecklistActivity : AppCompatActivity() {
    private lateinit var binding:ActivityChecklistBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChecklistBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var gvChecklist: GridView =binding.gvChecklist

        gvChecklist.adapter=PendienteAdapter(this,cargarPendiente())

    }

    fun cargarPendiente():ArrayList<PendienteItem>{
        var list:ArrayList<PendienteItem> = ArrayList()

        list.add(PendienteItem(true,"Pendiente Lorem 1"))
        list.add(PendienteItem(false,"Pendiente Lorem 2"))
        list.add(PendienteItem(true,"Pendiente Lorem 4"))
        list.add(PendienteItem(true,"Pendiente Lorem 4"))
        list.add(PendienteItem(false,"Pendiente Lorem 5"))


        Log.d("lista ChecklBox", list.toString())
        return list
    }

    private class PendienteAdapter: BaseAdapter {


        var items = ArrayList<PendienteItem>()
        var contexto: Context? = null

        constructor(context: Context, items: ArrayList<PendienteItem>){
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
            var vista = inflador.inflate(R.layout.item_checklist, null)


            var txtTitulo: TextView = vista.findViewById(R.id.text_pendiente)
            var checkboxPendiente: CheckBox = vista.findViewById(R.id.checkbox_pendiente)
            txtTitulo.setOnClickListener {
                var intento = Intent(contexto, ActivityProyecto::class.java)
                // TODO: agregar los detalles del proyecto seleccionado
                contexto!!.startActivity(intento)
            }


            txtTitulo.text=item.descripcion
            checkboxPendiente.isChecked= item.terminado

            Log.d(" ChecklBox:", checkboxPendiente.isChecked.toString())
            return vista


        }


    }

}