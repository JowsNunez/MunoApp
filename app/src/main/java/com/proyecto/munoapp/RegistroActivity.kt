package com.proyecto.munoapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.proyecto.munoapp.databinding.ActivityRegistroBinding

class RegistroActivity: AppCompatActivity() {
    private lateinit var binding: ActivityRegistroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var tv_iniciar = binding.tvIniciar

        tv_iniciar.setOnClickListener {
            var intent =  Intent(this,LoginActivity::class.java)
            startActivity(intent)


        }

    }
}