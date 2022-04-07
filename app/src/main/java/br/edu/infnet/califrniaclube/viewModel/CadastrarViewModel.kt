package br.edu.infnet.califrniaclube.viewModel

import android.util.Log
import android.view.LayoutInflater
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.edu.infnet.califrniaclube.R
import br.edu.infnet.califrniaclube.RegisterActivity
import br.edu.infnet.califrniaclube.databinding.ActivityRegisterBinding
import br.edu.infnet.califrniaclube.enderecoModel.Endereco
import br.edu.infnet.califrniaclube.network.EnderecoApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CadastrarViewModel : ViewModel() {
    private lateinit var binding: ActivityRegisterBinding
    fun getEndereco(cep: String) {
        val layoutInflater = LayoutInflater.from(RegisterActivity())
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        var enderecoJson : Endereco
        viewModelScope.launch(Dispatchers.IO) {
            enderecoJson =
                EnderecoApi.retrofitService.getEndereco(cep)//)cep?.value!!)
            var teste = enderecoJson.logradouro.toString()
            Log.i("API", enderecoJson.logradouro.toString())
            Log.w("TAG", enderecoJson.toString())

            // Passar os dados do endereco para as vairiáveis locais
            // Trata o endereço json e salva nas variávei
            binding.rua.setText(enderecoJson.logradouro.toString(), TextView.BufferType.EDITABLE)
            binding.bairro.setText(enderecoJson.bairro.toString(), TextView.BufferType.EDITABLE)
            binding.cidade.setText(enderecoJson.estado.toString(), TextView.BufferType.EDITABLE)
        }
    }
}