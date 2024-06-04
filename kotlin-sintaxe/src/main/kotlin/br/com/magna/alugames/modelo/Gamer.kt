package br.com.magna.alugames.modelo

import br.com.magna.alugames.br.com.magna.alugames.modelo.Jogo
import java.util.*
import kotlin.random.Random

data class Gamer(var nome: String, var email: String) {
    var dataNascimento: String? = null
    var usuario: String? = null
        set(value) {
            field = value // field significa o próprio campo da propertie. No caso, usuario
            if (idInterno.isNullOrBlank()) {
                criarIdInterno()
            }
        }

    var idInterno: String? = null
        private set

    val jogosBuscados = mutableListOf<Jogo?>()

    constructor(nome: String, email: String, dataNascimento: String, usuario: String)
            : this(nome, email) {
        this.dataNascimento = dataNascimento
        this.usuario = usuario
        criarIdInterno()
    }

    // init é chamado antes da criação do objeto. Ele é usado para fazer algum tipo de validação antes
    // de criar o objeto
    init {
        if (this.nome.isNullOrBlank()) {
            throw IllegalArgumentException("Nome está em branco")
        }
        this.email = validarEmail()
    }

    override fun toString(): String {
        return "Gamer(nome='$nome'\n" +
                "email='$email'\n" +
                "dataNascimento=$dataNascimento\n" +
                "usuario=$usuario\n" +
                "idInterno=$idInterno)"
    }

    fun criarIdInterno() {
        val numero = Random.nextInt(10000)
        val tag = String.format("%04d", numero)
        idInterno = "$usuario#$tag"
    }

    fun validarEmail(): String {
        val regex = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")

        if (regex.matches(email)) {
            return email
        } else {
            throw IllegalArgumentException("Email inválido")
        }
    }

    /*
        companion object's são objetos estáticos pertencentes à um outro objeto. Kotlin não possui
        a palavra chave static, portanto, usamos os companion object's para substituir o comportamento
        do static
    */
    companion object {
        fun criarGamer(leitura: Scanner): Gamer {
            println("Boas vindas ao AluGames! Vamos fazer seu cadastro. Digite seu nome:")
            val nome = leitura.nextLine()
            println("Digite seu e-mail:")
            val email = leitura.nextLine()
            println("Deseja completar seu cadastro com usuário e data de nascimento? (S/N)")
            val opcao = leitura.nextLine()

            if (opcao.equals("s", ignoreCase = true)) {
                println("Digite sua data de nascimento(DD/MM/AAAA):")
                val nascimento = leitura.nextLine()
                println("Digite seu nome de usuário:")
                val usuario = leitura.nextLine()

                return Gamer(nome, email, nascimento, usuario)
            } else {
                return Gamer(nome, email)
            }
        }
    }
}
