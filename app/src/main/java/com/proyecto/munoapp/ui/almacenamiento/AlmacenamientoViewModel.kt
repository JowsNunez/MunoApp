package com.proyecto.munoapp.ui.almacenamiento

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AlmacenamientoViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is almacenamiento Fragment"
    }
    val text: LiveData<String> = _text
}