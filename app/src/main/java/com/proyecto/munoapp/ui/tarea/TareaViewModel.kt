package com.proyecto.munoapp.ui.tarea

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.proyecto.munoapp.model.TareaItem

class TareaViewModel : ViewModel() {
    private val items: List<TareaItem> = cargarTarea() as List<TareaItem>

    fun getTareas(): LiveData<List<TareaItem>> {
        return MutableLiveData<List<TareaItem>>().apply {
            value = items
        }
    }

    private fun cargarTarea(): List<TareaItem> {
        val tareas = mutableListOf<TareaItem>()
        for (i in 1..10) {
            tareas.add(TareaItem("Tarea $i", "Descripción $i"))
        }
        return tareas
    }
}