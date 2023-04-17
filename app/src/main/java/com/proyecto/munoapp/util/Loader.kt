package com.proyecto.munoapp.util

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import java.util.TimerTask

data class Loader(var intent: Intent,var context: Context): TimerTask() {

    var compat: AppCompatActivity?=null
    override fun run() {
                compat=context as AppCompatActivity
                context.startActivity(intent)
                compat!!.finish()


    }
}

