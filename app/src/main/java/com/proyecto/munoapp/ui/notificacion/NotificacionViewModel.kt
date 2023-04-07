package com.proyecto.munoapp.ui.notificacion

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NotificacionViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is Notificacion Fragment"
    }
    val text: LiveData<String> = _text
}