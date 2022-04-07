package br.edu.infnet.califrniaclube

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import br.edu.infnet.califrniaclube.memberModel.Member
import br.edu.infnet.califrniaclube.databinding.ActivityRegisterBinding
import br.edu.infnet.califrniaclube.network.EnderecoApi
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import androidx.lifecycle.viewModelScope
import br.edu.infnet.califrniaclube.viewModel.CadastrarViewModel


class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    val db = Firebase.firestore
    private val TAG = "DataBase"
    private val viewModel = CadastrarViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonCep.setOnClickListener {
            var cep = findViewById<EditText>(R.id.cep).text.toString()
            var endereco = viewModel.getEndereco(cep)
        }

        binding.buttonCadastrar.setOnClickListener {
            var nome = findViewById<EditText>(R.id.nome).text.toString()
            var idade = findViewById<EditText>(R.id.idade).text.toString()
            var telefone = findViewById<EditText>(R.id.telefone).text.toString()
            var email = findViewById<EditText>(R.id.email).text.toString()
            var cep = findViewById<EditText>(R.id.cep).text.toString()
            var rua = findViewById<EditText>(R.id.rua).text.toString()
            var bairro = findViewById<EditText>(R.id.bairro).text.toString()
            var cidade = findViewById<EditText>(R.id.cidade).text.toString()

            var member = Member(nome, idade, telefone, email, cep, rua, bairro, cidade)

            db.collection("members")
                .add(member)
                .addOnSuccessListener { documentReference ->
                    Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                    Toast.makeText(this, "Membro cadastrado com sucesso!", Toast.LENGTH_LONG).show()
                    finish()
                }
                .addOnFailureListener { e ->
                    Log.w(TAG, "Error adding document", e)
                }
        }
    }
}