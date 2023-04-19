package com.proyecto.munoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.proyecto.munoapp.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var btn_inicar = binding.btnIniciar

        var btn_regresar = binding.btnRegresar

        btn_inicar.setOnClickListener {
            var intent =  Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()

        }

        btn_regresar.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
            finish()
        }


    }

}
