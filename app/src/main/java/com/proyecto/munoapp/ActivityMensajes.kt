package com.proyecto.munoapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.GridLayout
import android.widget.GridView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.proyecto.munoapp.databinding.ActivityMensajesBinding
import com.proyecto.munoapp.model.MensajeItem
import com.proyecto.munoapp.model.MiembroItem
import com.proyecto.munoapp.model.ProyectoItem


class ActivityMensajes :AppCompatActivity(){
    private lateinit var binding:ActivityMensajesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMensajesBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val btn_regresar = binding.btnRegresar

        val gridMensajes:GridView = binding.gridMensajes

        gridMensajes.adapter=cargarMensajes(this)


        btn_regresar.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
            finish()
        }



    }

    private fun cargarMensajes(context: Context):MensajeAdapter{
        val list: ArrayList<MensajeItem> =ArrayList()

        list.add(MensajeItem("Conenido de mensaje 1", MiembroItem("Usuario1,","estado")))
        list.add(MensajeItem("Conenido de mensaje 2", MiembroItem("Usuario2,","estado")))
        list.add(MensajeItem("Conenido de mensaje 3", MiembroItem("Usuario1,","estado")))
        list.add(MensajeItem("Conenido de mensaje 4", MiembroItem("Usuario2,","estado")))
        list.add(MensajeItem("Conenido de mensaje 5", MiembroItem("Usuario1,","estado")))

        return MensajeAdapter(context,list)

    }


    private class MensajeAdapter: BaseAdapter {


        var items = ArrayList<MensajeItem>()
        var contexto: Context? = null

        constructor(context: Context, items: ArrayList<MensajeItem>){
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
            var vista = inflador.inflate(R.layout.item_mensaje_receptor, null)

            if(position%2==0){

                var txtTitulo: TextView = vista.findViewById(R.id.tv_usuario_receptor)
                var txtDescripcion: TextView = vista.findViewById(R.id.tv_contenido_receptor)


                txtTitulo.text=item.miembroItem.nombre
                txtDescripcion.text=item.contenido

                return vista
            }


            var vista_remitente = inflador.inflate(R.layout.item_mensaje_remitente, null)


            var txtTitulo: TextView = vista_remitente.findViewById(R.id.tv_usuario_remitente)
            var txtDescripcion: TextView = vista_remitente.findViewById(R.id.tv_contenido_remitente)


            txtTitulo.text=item.miembroItem.nombre
            txtDescripcion.text=item.contenido

            return vista_remitente


        }


    }

}