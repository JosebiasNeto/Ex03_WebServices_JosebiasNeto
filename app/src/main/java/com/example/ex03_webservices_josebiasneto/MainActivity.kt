package com.example.ex03_webservices_josebiasneto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.ex03_webservices_josebiasneto.RetrofitBuilder.cepAPI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    private lateinit var cep: CEP

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val btn = findViewById<Button>(R.id.btn_search)
        val et = findViewById<EditText>(R.id.etCep)
        btn.setOnClickListener {
            if(et.text.toString().length == 8 && checkOnlyNumbers(et.text.toString())){
                getAddress(et.text.toString())
            }
        }
    }

    private fun getAddress(nCEP: String){
        CoroutineScope(Dispatchers.IO).launch {
            cep = cepAPI.getCep(nCEP)

            withContext(Dispatchers.Main){
                updateUI()
            }
        }
    }

    private fun updateUI(){
        findViewById<TextView>(R.id.addressResponse).text = cep.logradouro
        findViewById<TextView>(R.id.regionResponse).text = cep.bairro
        findViewById<TextView>(R.id.cityResponse).text = cep.localidade
        findViewById<TextView>(R.id.stateResponse).text = cep.uf
    }

    private fun checkOnlyNumbers(str: String): Boolean{
        for(element in str){
            if(!element.isDigit()) return false
        }
        return true
    }
}