package com.proyecto.munoapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.proyecto.munoapp.databinding.ActivityMiembrosBinding
import com.proyecto.munoapp.model.MiembroItem

class MiembrosActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMiembrosBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMiembrosBinding.inflate(layoutInflater)

        setContentView(binding.root)


        var btn_regresar = binding.btnRegresarMiembros

        var gvMiembro = binding.gvBusquedaMiembros
        var gvUsuario = binding.gvBusquedaUsuario

        btn_regresar.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
            finish()
        }


        gvMiembro.adapter = MiembroAdapter(this,cargarMiembro(),"item_buscar_miembro")
        gvUsuario.adapter = MiembroAdapter(this,cargarUsuario(),"")



    }

    fun cargarUsuario():ArrayList<MiembroItem>{

        var list = ArrayList<MiembroItem>()

        list.add(MiembroItem("Usuario Nombre 5","Escribiendo Documentación"))

        list.add(MiembroItem("Usuario Nombre 6","Realizando analisis"))

        list.add(MiembroItem("Usuario Nombre 8","Creando Graficas"))
        list.add(MiembroItem("Usuario Nombre 8","Creando Graficas"))
        list.add(MiembroItem("Usuario Nombre 8","Creando Graficas"))
        list.add(MiembroItem("Usuario Nombre 8","Creando Graficas"))
        list.add(MiembroItem("Usuario Nombre 8","Creando Graficas"))

        return list

    }

    fun cargarMiembro():ArrayList<MiembroItem>{

        var list = ArrayList<MiembroItem>()

        list.add(MiembroItem("Usuario Nombre 1","Escribiendo Documentación"))

        list.add(MiembroItem("Usuario Nombre 2","Realizando analisis"))

        list.add(MiembroItem("Usuario Nombre 3","Creando Graficas"))
        list.add(MiembroItem("Usuario Nombre 3","Creando Graficas"))
        list.add(MiembroItem("Usuario Nombre 3","Creando Graficas"))
        list.add(MiembroItem("Usuario Nombre 3","Creando Graficas"))


        return list

    }

    private class MiembroAdapter: BaseAdapter {


        var items = ArrayList<MiembroItem>()
        var contexto: Context? = null
        var layout:String ?=null

        constructor(context: Context, items: ArrayList<MiembroItem>,layout:String){
            this.contexto=context
            this.items=items
            this.layout=layout

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
            var vista:View
            if(layout!="item_buscar_miembro") {
                vista = inflador.inflate(R.layout.item_buscar_usuario, null)
            }else{
                vista = inflador.inflate(R.layout.item_buscar_miembro, null)

            }


            var nombre: TextView = vista.findViewById(R.id.miembro_nombre)


            nombre.text=item.nombre


            return vista


        }


    }
}