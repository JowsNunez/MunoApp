package com.proyecto.munoapp.ui.proyecto

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.proyecto.munoapp.R
import com.proyecto.munoapp.util.DialogListener
import java.util.*

class ProyectoDialog(context: Context): DialogFragment(){

    private lateinit var storage: FirebaseFirestore
    private lateinit var auth: FirebaseAuth
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireContext())

        val inflater = requireActivity().layoutInflater
        val view = inflater.inflate(R.layout.dialog_proyecto, null)
        auth = Firebase.auth
        storage = FirebaseFirestore.getInstance()

        val positiveButton: Button = view.findViewById(R.id.button_add_proyecto)
        val txtTitulo: EditText = view.findViewById(R.id.txt_nombre)
        val txtDescripcion : EditText = view.findViewById(R.id.txt_descripcion)
        val userEmail = auth.currentUser?.email
        positiveButton.setOnClickListener {
         storage.collection("proyectos").document()
             .set(
                hashMapOf(
                    "titulo" to txtTitulo.text.toString(),
                    "descripcion" to txtDescripcion.text.toString(),
                    "miembros" to  listOf(hashMapOf("email" to userEmail)),
                    "pendientes" to  listOf(hashMapOf("titulo" to "")),
                    "canales" to  listOf(hashMapOf("titulo" to "")),
                    "fecha" to Date()
                )
            )
            // para guardar
            Toast.makeText(context,"Se a creado ",Toast.LENGTH_SHORT).show()
            // para guardar
            Toast.makeText(context,"Se a creado ",Toast.LENGTH_SHORT).show()
            dismiss()
        }

        val negativeButton:ImageButton = view.findViewById(R.id.button_close_proyect)
        negativeButton.setOnClickListener {
            // para cancelar

            dismiss()
        }

        builder
            .setView(view)
            .setCancelable(false)




        return builder.create()
    }

    override fun onStart() {
        // evitar que se cierre el dialogo al hacer touch fuera de el
        super.onStart()
        dialog?.setCanceledOnTouchOutside(false)
        val window = dialog?.window

        window?.setLayout(750, WindowManager.LayoutParams.WRAP_CONTENT)
    }

    private lateinit var listener: DialogListener

    fun setOnDismissListener(listener: DialogListener) {
        this.listener = listener
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        listener.onDismis()
    }


}