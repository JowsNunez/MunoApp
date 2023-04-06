package com.proyecto.munoapp.util

import android.content.Context
import android.content.Intent
import java.util.TimerTask

data class Loader(var intent: Intent,var context: Context): TimerTask() {

    override fun run() {

                context.startActivity(intent)

    }
}

