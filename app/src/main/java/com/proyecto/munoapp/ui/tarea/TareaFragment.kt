package com.proyecto.munoapp.ui.tarea

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
import android.widget.GridView
import android.widget.ImageButton
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.proyecto.munoapp.ActivityTarea
import com.proyecto.munoapp.R
import com.proyecto.munoapp.databinding.FragmentProyectoBinding
import com.proyecto.munoapp.databinding.FragmentTareaBinding
import com.proyecto.munoapp.model.TareaItem
import com.proyecto.munoapp.ui.notificacion.NotificacionViewModel
import com.proyecto.munoapp.ui.proyecto.ProyectoDialog
import com.proyecto.munoapp.ui.proyecto.ProyectoViewModel
import com.proyecto.munoapp.util.DialogListener

class TareaFragment : Fragment() , DialogListener{
    private var _binding: FragmentTareaBinding? = null
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
        val tareaViewModel =
            ViewModelProvider(this).get(TareaViewModel::class.java)
        storage= FirebaseFirestore.getInstance()
        auth=Firebase.auth

        _binding = FragmentTareaBinding.inflate(inflater, container, false)
        val root: View = binding.root


        val gridProyecto: GridView = binding.gridProyecto




         cargar(root.context,gridProyecto)


        var buttonAdd: ImageButton = _binding!!.buttonAdd

        buttonAdd.setOnClickListener {
            val tareaDialog = TareaDialog(root.context)
            tareaDialog.setOnDismissListener(this)
            tareaDialog.show(parentFragmentManager,"TareaDialog")
        }

        return root



    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun cargar(context: Context, view: GridView){
        var items: ArrayList<TareaItem> = ArrayList()

        val email =  auth.currentUser?.email.toString()
        val docRef= storage.collection("tareas").whereEqualTo("email", email)

        docRef.get().addOnCompleteListener { task ->
            if(task.isSuccessful){
                for (document in task.result) {
                    items.add(TareaItem(document.data.get("titulo").toString(),document.data.get("descripcion").toString()))
                }
                if(items.isNotEmpty()){
                   view.adapter =TareaAdapter(context, items)
                }

            }
        }



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

    override fun onDismis() {
        this.cargar(binding.root.context,binding.gridProyecto)
    }

}