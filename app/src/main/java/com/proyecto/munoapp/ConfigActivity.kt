package com.proyecto.munoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import com.proyecto.munoapp.databinding.ActivityConfigBinding

class ConfigActivity : AppCompatActivity() {
    private lateinit var binding: ActivityConfigBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_config)

        binding = ActivityConfigBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var btnRegresar:ImageButton = binding.btnRegresarConfig

        btnRegresar.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()

        }

    }
}