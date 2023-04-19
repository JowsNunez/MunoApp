package com.proyecto.munoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.proyecto.munoapp.util.Loader
import java.util.Timer


class LoadActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_load)

        var intent = Intent(this,RegistroActivity::class.java)
        val loader:Loader = Loader(intent,this);

        var tasker:Timer = Timer()
        tasker.schedule(loader,1500);


        }
    }

