package com.proyecto.munoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.proyecto.munoapp.databinding.ActivityConfigProyectoBinding

class ActivityConfigProyecto : AppCompatActivity() {
    private lateinit var binding: ActivityConfigProyectoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfigProyectoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var btn_regresar = binding.btnRegresar
        var btn_modificar = binding.btnModificarMiembros

        btn_modificar.setOnClickListener {
            var intent: Intent = Intent(this,MiembrosActivity::class.java)
            startActivity(intent)

        }
        btn_regresar.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }



    }
}