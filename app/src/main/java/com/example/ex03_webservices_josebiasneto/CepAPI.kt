package com.example.ex03_webservices_josebiasneto

import retrofit2.http.GET
import retrofit2.http.Path

interface CepAPI {

    @GET("{nCEP}/json")
    suspend fun getCep(@Path("nCEP") nCEP: String): CEP

}