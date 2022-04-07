package br.edu.infnet.califrniaclube

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.edu.infnet.califrniaclube.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.cadastro.setOnClickListener{
            registrarMembrosActivity()
        }

        binding.lista.setOnClickListener{
            listarMembrosActivity()
        }
    }

    fun listarMembrosActivity() {
        var listActivity = Intent(this, ListActivity::class.java)
        startActivity(listActivity)
    }

    fun registrarMembrosActivity() {
        var registerActivity = Intent(this, RegisterActivity::class.java)
        startActivity(registerActivity)
    }
}