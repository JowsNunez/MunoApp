package com.proyecto.munoapp.ui.proyecto

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ProyectoViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is Proyecto Fragment"
    }
    val text: LiveData<String> = _text
}