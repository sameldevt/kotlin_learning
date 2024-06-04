package br.com.magna.alugames.servicos

import br.com.magna.alugames.br.com.magna.alugames.modelo.InfoJogo
import com.google.gson.Gson
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse.BodyHandlers

class ConsumoApi {
    fun buscaJogo(id: String) : InfoJogo {
        var endereco = "https://www.cheapshark.com/api/1.0/games?id=$id"

        val client: HttpClient = HttpClient.newHttpClient()
        val request = HttpRequest.newBuilder()
            .uri(URI.create(endereco))
            .build()

        val response = client
            .send(request, BodyHandlers.ofString())

        val json = response.body()

        println(json)

        val gson = Gson()
        val meuInfoJogo = gson.fromJson(json, InfoJogo::class.java)

        return meuInfoJogo
    }
}