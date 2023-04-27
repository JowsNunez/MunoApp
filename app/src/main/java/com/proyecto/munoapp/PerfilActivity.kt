package com.proyecto.munoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.proyecto.munoapp.databinding.ActivityPerfilBinding
import com.proyecto.munoapp.util.AutenticacionManager

class PerfilActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPerfilBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPerfilBinding.inflate(layoutInflater)
        setContentView(binding.root)


        var btn_portafolio = binding.btnPortafolio
        val buttonConfig = binding.btnConfiguracion
        val btn_regresar = binding.btnRegresarPerfil
        val btn_amigos = binding.btnAmigos
        val btn_signOut = binding.btnSignOut


        val textUserName = binding.userName

        textUserName.text=Firebase.auth.currentUser?.displayName


        btn_portafolio.setOnClickListener {
            var intent = Intent(this,PortafolioActivity::class.java)
            startActivity(intent)
        }

        buttonConfig.setOnClickListener{
            val intent: Intent = Intent(this, ConfigActivity::class.java)
            startActivity(intent)
        }

        btn_amigos.setOnClickListener {
            val intent = Intent(this, ActivityMensajes::class.java)
            startActivity(intent)
        }

        btn_regresar.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        btn_signOut.setOnClickListener {
            Firebase.auth.signOut()
            GoogleSignIn.getClient(this, GoogleSignInOptions.DEFAULT_SIGN_IN).signOut().addOnCompleteListener {
                val intent=Intent(this ,LoadActivity::class.java)
                startActivity(intent)
                finish()
            }

        }


    }
}