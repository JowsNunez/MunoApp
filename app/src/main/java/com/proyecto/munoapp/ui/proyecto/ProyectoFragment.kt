package com.proyecto.munoapp.ui.proyecto

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.GridView
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.lifecycle.ViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.proyecto.munoapp.ActivityProyecto
import com.proyecto.munoapp.R
import com.proyecto.munoapp.databinding.FragmentProyectoBinding
import com.proyecto.munoapp.model.MiembroItem
import com.proyecto.munoapp.model.ProyectoItem
import com.proyecto.munoapp.model.TareaItem
import com.proyecto.munoapp.ui.tarea.TareaFragment
import com.proyecto.munoapp.util.DialogListener
import java.util.*
import kotlin.collections.ArrayList

class ProyectoFragment : Fragment(),DialogListener {

    private var _binding: FragmentProyectoBinding? = null
    private lateinit var storage: FirebaseFirestore

    private lateinit var auth: FirebaseAuth


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

        auth= Firebase.auth
        storage = FirebaseFirestore.getInstance()


        val gridProyecto: GridView = binding.gridProyecto




        cargar(root.context,gridProyecto)


        var buttonAdd:ImageButton = _binding!!.buttonAdd

        buttonAdd.setOnClickListener {
            val proyectoDialog = ProyectoDialog(root.context)
            proyectoDialog.setOnDismissListener(this)
            proyectoDialog.show(parentFragmentManager,"ProyectoDialog")
        }

        return root



    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun cargar(context: Context, view:GridView) {

        var proyectos: ArrayList<ProyectoItem> = ArrayList()


        val email =  auth.currentUser?.email.toString()
        val docRef= storage.collection("proyectos").whereArrayContains("miembros", hashMapOf("email" to email))


        docRef.get().addOnCompleteListener { task ->
            if(task.isSuccessful){
                for (document in task.result) {
                    Log.d("",document.data.toString())
                    proyectos.add(ProyectoItem(document.data.get("titulo").toString(),document.data.get("descripcion").toString(),1))
                }

                view.adapter=ProyectoAdapter(context,proyectos)
            }
        }


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
            var cardProyecto:LinearLayout = vista.findViewById(R.id.contenido_proyecto)

            cardProyecto.setOnClickListener {
                var intento = Intent(contexto, ActivityProyecto::class.java)
                intento.putExtra("titulo",item.titulo)
                // TODO: agregar los detalles del proyecto seleccionado
                contexto!!.startActivity(intento)
            }


            txtTitulo.text=item.titulo
            txtDescripcion.text=item.descripcion

            return vista


        }


    }

    override fun onDismis() {
        cargar(binding.root.context,binding.gridProyecto)
    }

}