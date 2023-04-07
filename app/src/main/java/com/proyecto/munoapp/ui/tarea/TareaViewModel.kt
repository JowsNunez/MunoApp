package com.proyecto.munoapp.ui.tarea

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TareaViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is Tarea Fragment"
    }
    val text: LiveData<String> = _text
}