package com.proyecto.munoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.proyecto.munoapp.databinding.ActivityPerfilBinding

class PerfilActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPerfilBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPerfilBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var btn_portafolio = binding.btnPortafolio

        val buttonConfig = binding.btnConfiguracion
        var btn_regresar = binding.btnRegresarPerfil



        btn_portafolio.setOnClickListener {
            var intent = Intent(this,PortafolioActivity::class.java)
            startActivity(intent)
        }

        buttonConfig.setOnClickListener{
            val intent: Intent = Intent(this, ConfigActivity::class.java)
            startActivity(intent)
        }

        btn_regresar.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
            finish()
        }


    }
}