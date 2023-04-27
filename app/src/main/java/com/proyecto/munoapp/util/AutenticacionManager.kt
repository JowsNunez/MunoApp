package com.proyecto.munoapp.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AutenticacionManager {


    companion object {
        private lateinit var auth: FirebaseAuth
        fun verificarSesionInactivaFirebase(context: Context, clazz: Class<*>) {
             auth = Firebase.auth
            val currentUser = auth.currentUser
            if (currentUser == null) {
                // Usuario no autenticado, redirigir a pantalla de inicio de sesión
                val intent = Intent(context, clazz)
                val activity:AppCompatActivity =context as AppCompatActivity
                context.startActivity(intent)
                activity.finish()


            }
        }

        fun verificarSesionActivaFirebase(context: Context, clazz: Class<*>) {
            auth = Firebase.auth
            val currentUser = auth.currentUser
            if (currentUser != null) {
                // Usuario autenticado, abrir actividad Main
                val activity:AppCompatActivity =context as AppCompatActivity
                val intent = Intent(context, clazz)
                context.startActivity(intent)
                activity.finish()

            }
        }


    }


}