package com.proyecto.munoapp.ui.mensaje

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MensajeViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is Mensaje Fragment"
    }
    val text: LiveData<String> = _text
}