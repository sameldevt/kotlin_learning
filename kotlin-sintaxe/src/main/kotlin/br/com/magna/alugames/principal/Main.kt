package br.com.magna.alugames.principal

import br.com.magna.alugames.br.com.magna.alugames.modelo.Jogo
import br.com.magna.alugames.modelo.Gamer
import br.com.magna.alugames.servicos.ConsumoApi
import transformarEmIdade
import java.security.Principal
import java.util.*

fun main() {

    /*
    val meuJogo = Jogo("Batman: Arkham Asylum", "https:\\/\\/cdn.fanatical.com\\/production\\/product\\/400x225\\/105f34ca-7757-47ad-953e-7df7f016741e.jpeg", "Jogo do Batman")

    // Instanciando uma classe com a ordem dos parametros personalizada
    val novoJogo = Jogo(
        capa = "https:\\/\\/cdn.fanatical.com\\/production\\/product\\/400x225\\/105f34ca-7757-47ad-953e-7df7f016741e.jpeg",
        titulo = "Batman: Arkham Asylum",
        descricao = "Jogo do Batman"
    )
    */

    val leitura = Scanner(System.`in`)
    val gamer = Gamer.criarGamer(leitura)
    println("Cadastro concluido com sucesso. Dados do Gamer")
    println(gamer)

    /*
        Na linha abaixo, estamos utilizando um função de extensão da classe String. Funções de extensão,
        como o próprio nome diz, extendem o comportamento de uma classe sem a necessidade de herança.

        Abaixo temos a implementação dessa função de extensão, que no caso, modifica a classe String.
        fun String.transformarEmIdade(): Int {
            val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
            var idade = 0
            val dataNascimento = LocalDate.parse(this, formatter)
            val hoje = LocalDate.now()
            idade = Period.between(dataNascimento, hoje).years

            return idade
        }
     */
    println("Idade do gamer: ${gamer.dataNascimento?.transformarEmIdade()}")

    do {
        print("Digite um código de jogo para buscar: ")
        val busca = leitura.nextLine()

        val buscaApi = ConsumoApi()
        val informacaoJogo = buscaApi.buscaJogo(busca)

        var meuJogo: Jogo? = null

        // Maneira utilizada no Kotlin para tratamento de exceções
        val resultado = runCatching {
            meuJogo = Jogo(
                titulo = informacaoJogo.info.title,
                capa = informacaoJogo.info.thumb
            )
        }

        resultado.onFailure {
            println("Jogo não existe. Tente outro código.")
        }

        resultado.onSuccess {
            print("Deseja adicionar uma descrição personalizada? S/N")
            val opcao = leitura.nextLine()

            if (opcao.equals("S", ignoreCase = true)) {
                print("Digite a descrição personalizada do jogo: ")
                val descricaoPersonalizada = leitura.nextLine()
                meuJogo?.descricao = descricaoPersonalizada
            } else {
                meuJogo?.descricao = meuJogo?.titulo
            }

            gamer.jogosBuscados.add(meuJogo)
        }

        print("Deseja buscar um novo jogo? S/N")
        val resposta = leitura.nextLine()

    } while (resposta.equals("s", ignoreCase = true))

    println("Jogos buscados:")
    println(gamer.jogosBuscados)

    println("Jogos ordenados por titulo:")
    gamer.jogosBuscados.sortBy {
        it?.titulo
    }

    gamer.jogosBuscados.forEach {
        println("Titulo: ${it?.titulo}")
    }

    println("Jogos filtrados: ")
    val jogosFiltrados = gamer.jogosBuscados.filter {
        it?.titulo?.contains("Batman", ignoreCase = true) ?: false
    }
    println(jogosFiltrados)

    println("Deseja excluir um jogo da lista original? S/N")
    val opcao = leitura.nextLine()

    if (opcao.equals("s", true)) {
        println(gamer.jogosBuscados)
        print("\nInforme a posição do jogo que deseja excluir: ")
        val posicao = leitura.nextInt()

        gamer.jogosBuscados.removeAt(posicao)
    }

    println("Lista atualizada: ")
    println(gamer.jogosBuscados)

    println("Busca realizada com sucesso")
}