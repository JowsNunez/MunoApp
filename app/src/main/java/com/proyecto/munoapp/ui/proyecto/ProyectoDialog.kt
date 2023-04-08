package com.proyecto.munoapp.ui.proyecto

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.proyecto.munoapp.R

class ProyectoDialog(context: Context): DialogFragment(){

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireContext())

        val inflater = requireActivity().layoutInflater
        val view = inflater.inflate(R.layout.dialog_proyecto, null)

        val positiveButton: Button = view.findViewById(R.id.button_add_proyecto)
        positiveButton.setOnClickListener {
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


}