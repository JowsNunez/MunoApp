package com.proyecto.munoapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.proyecto.munoapp.databinding.ActivityRegistroBinding
import com.proyecto.munoapp.util.AutenticacionManager

class RegistroActivity: AppCompatActivity() {
    private lateinit var binding: ActivityRegistroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)


        var tv_iniciar = binding.tvIniciar
        AutenticacionManager.verificarSesionActivaFirebase(this,MainActivity::class.java)

        tv_iniciar.setOnClickListener {
            val intent =  Intent(this,LoginActivity::class.java)
            startActivity(intent)
            finish()


        }

    }
}