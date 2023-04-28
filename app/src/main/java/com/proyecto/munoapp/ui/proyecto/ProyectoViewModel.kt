package com.proyecto.munoapp.ui.proyecto

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.proyecto.munoapp.model.ProyectoItem

class ProyectoViewModel : ViewModel() {
    private val items: MutableLiveData<List<ProyectoItem>> = MutableLiveData(emptyList())

    private val storage: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    fun getProyects(): LiveData<List<ProyectoItem>> {
        cargarProyectos()
        return MutableLiveData<List<ProyectoItem>>().apply {
            value = items.value
        }
    }

    private fun cargarProyectos() {
        val proyectos = mutableListOf<ProyectoItem>()



    }
}