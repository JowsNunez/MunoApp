package com.proyecto.munoapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.proyecto.munoapp.databinding.ActivityRegistroBinding
import com.proyecto.munoapp.util.AutenticacionManager

class RegistroActivity: AppCompatActivity() {
    private lateinit var binding: ActivityRegistroBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var storage:FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth
        storage= FirebaseFirestore.getInstance()

        val tv_iniciar = binding.tvIniciar
        val txtNombre = binding.txtfieldNombre
        val txtEmail = binding.txtfieldCorreo
        val txtContrasena = binding.txtfieldContrasena
        val txtContrasena2 = binding.txtfieldCContrasena

        val btnRegistro = binding.txtRegistrar
        AutenticacionManager.verificarSesionActivaFirebase(this, MainActivity::class.java)

        tv_iniciar.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()


        }



        btnRegistro.setOnClickListener {
            var nombre: String = txtNombre.text.toString()
            var correo: String = txtEmail.text.toString()
            var contra1: String = txtContrasena.text.toString()
            var contra2: String = txtContrasena2.text.toString()
            if (!correo.isNullOrBlank() && !contra1.isNullOrBlank() &&
                !contra2.isNullOrBlank() && !nombre.isNullOrBlank()
            ) {

                if (contra1 == contra2) {

                    registrarFirebase(correo, contra1,nombre)


                } else {
                    Toast.makeText(
                        this, "Las contraseña no coinciden",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            } else {
                Toast.makeText(
                    this, "Ingresar datos",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }


    private fun registrarFirebase(email: String, password: String,nombre:String) {

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    //Log.d(TAG, "createUserWithEmail:success")
                    val user = auth.currentUser

                    storage.collection("users").document().set(
                        hashMapOf(
                            "uid" to auth.currentUser?.uid,
                            "nombre" to nombre,
                            "email" to email,
                            "estado" to "",
                            "imagen" to "",
                            "telefono" to ""
                        )
                    )

                    Toast.makeText(
                        baseContext, "${user?.email} se ha creado correctamente",
                        Toast.LENGTH_SHORT
                    ).show()

                    //updateUI(user)
                } else {

                    Toast.makeText(
                        baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()

                }
            }
    }
}