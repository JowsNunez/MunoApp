package com.proyecto.munoapp.ui.proyecto

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.proyecto.munoapp.model.ProyectoItem

class ProyectoViewModel : ViewModel() {
    private val items: List<ProyectoItem> = cargarProyectos() as List<ProyectoItem>

    fun getProyects(): LiveData<List<ProyectoItem>> {
        return MutableLiveData<List<ProyectoItem>>().apply {
            value = items
        }
    }

    private fun cargarProyectos(): List<ProyectoItem> {
        val proyectos = mutableListOf<ProyectoItem>()
        for (i in 1..10) {
            proyectos.add(ProyectoItem("Proyecto $i", "Descripción $i", i))
        }
        return proyectos
    }
}