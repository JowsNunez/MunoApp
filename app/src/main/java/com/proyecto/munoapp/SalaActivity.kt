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
import android.widget.Toast
import com.proyecto.munoapp.databinding.ActivitySalaBinding
import com.proyecto.munoapp.model.MiembroItem
import com.proyecto.munoapp.model.PendienteItem

class SalaActivity : AppCompatActivity() {
    private lateinit var  binding:ActivitySalaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding = ActivitySalaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var gvMiembros: GridView =binding.gvMiembros

        var txtActividad  =binding.textEditarActividad
        var btnRegresar  =binding.btnRegresarSala

        btnRegresar.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
            finish()
        }

        txtActividad.setOnClickListener {
            Toast.makeText(this,"TODO: crear popup",Toast.LENGTH_SHORT).show()
        }

        gvMiembros.adapter=MiembroAdapter(this,cargarMiembro())

    }

    private  fun cargarMiembro(): ArrayList<MiembroItem>{

        var list = ArrayList<MiembroItem>()

        list.add(MiembroItem("Usuario Nombre 1","Escribiendo Documentación"))

        list.add(MiembroItem("Usuario Nombre 2","Realizando analisis"))

        list.add(MiembroItem("Usuario Nombre 3","Creando Graficas"))

        return list
    }


    private class MiembroAdapter: BaseAdapter {


        var items = ArrayList<MiembroItem>()
        var contexto: Context? = null

        constructor(context: Context, items: ArrayList<MiembroItem>){
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
            var vista = inflador.inflate(R.layout.item_miembros, null)


            var nombre: TextView = vista.findViewById(R.id.miembro_nombre)
            var estado: TextView = vista.findViewById(R.id.miembro_estado)

            nombre.text=item.nombre
            estado.text=item.estado

            return vista


        }


    }
}