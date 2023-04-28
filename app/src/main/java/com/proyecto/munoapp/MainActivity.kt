package com.proyecto.munoapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.proyecto.munoapp.databinding.ActivityMainBinding
import com.proyecto.munoapp.util.AutenticacionManager

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var auth: FirebaseAuth
    private lateinit var storage: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: NavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
               R.id.navigation_tareas,   R.id.navigation_proyecto,R.id.navigation_mensaje, R.id.navigation_notificacion, R.id.navigation_almacenamiento
            )
        )
        AutenticacionManager.verificarSesionInactivaFirebase(this,LoginActivity::class.java);
        auth = Firebase.auth
        storage = FirebaseFirestore.getInstance()



        val buttonConfig = binding.btnConfig
        val img = binding.imgUser
        val textUser = binding.txtUserName


        storage.collection("users").whereEqualTo("email",auth.currentUser?.email)
            .get().addOnCompleteListener {  task->
                if(task.isSuccessful){
                    val document = task.result

                    if(!document.isEmpty){

                      textUser.text=  document.documents[0].data?.get("nombre").toString()

                    }
                }
        }


        buttonConfig.setOnClickListener{
            val intent: Intent = Intent(this, ConfigActivity::class.java)
            startActivity(intent)
        }

        img.setOnClickListener {
            val intent: Intent = Intent(this, PerfilActivity::class.java)
            startActivity(intent)
            finish()
        }

        navView.setupWithNavController(navController)
    }
}