package com.proyecto.munoapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.proyecto.munoapp.model.PortafolioItem

class PortafolioActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_portafolio)
        var gridView:GridView = findViewById(R.id.portafolio_items)
        var btn_regresar:ImageButton = findViewById(R.id.btn_regresar_portafolio)

        btn_regresar.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
            finish()
        }


        gridView.adapter=cargar()
    }


    private fun cargar():PortafolioAdapter{
        var items:ArrayList<PortafolioItem> = ArrayList()

        items.add(PortafolioItem("Proyecto 1","descripcion proyecto 1",1))
        items.add(PortafolioItem("Proyecto 2","descripcion proyecto 2",1))
        items.add(PortafolioItem("Proyecto 3","descripcion proyecto 3",1))
        items.add(PortafolioItem("Proyecto 4","descripcion proyecto 4",1))
        items.add(PortafolioItem("Proyecto 5","descripcion proyecto 5",1))
        items.add(PortafolioItem("Proyecto 6","descripcion proyecto 6",1))
        items.add(PortafolioItem("Proyecto 7","descripcion proyecto 7",1))
        items.add(PortafolioItem("Proyecto 8","descripcion proyecto 8",1))




       return PortafolioAdapter(this,items)

    }

    private class PortafolioAdapter : BaseAdapter{

        var items = ArrayList<PortafolioItem>()
        var contexto: Context? = null

        constructor(contexto: Context, items: ArrayList<PortafolioItem>) {
            this.contexto = contexto
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
            var vista = inflador.inflate(R.layout.item_portafolio, null)


            var txtTitulo: TextView = vista.findViewById(R.id.titulo)
            var txtDescripcion: TextView = vista.findViewById(R.id.descripcion)

            txtTitulo.text=item.titulo
            txtDescripcion.text=item.descripcion

            return vista


        }


    }
}