package com.proyecto.munoapp.ui.almacenamiento

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.proyecto.munoapp.R

class AdjuntarArchivoDialog:DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireContext())

        val inflater = requireActivity().layoutInflater
        val view = inflater.inflate(R.layout.pop_up_storage_2, null)




        builder
            .setView(view)
            .setCancelable(false)


        return builder.create()
    }
}