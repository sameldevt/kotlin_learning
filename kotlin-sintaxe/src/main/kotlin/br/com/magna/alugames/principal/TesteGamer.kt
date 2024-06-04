package br.com.magna.alugames.principal

import br.com.magna.alugames.modelo.Gamer

fun main(){
    val gamer1 = Gamer("Nome1", "Email1")
    println(gamer1)

    val gamer2 = Gamer("Nome2", "Email2", "01/01/1970", "Username1")
    println(gamer2)

    /*
        let é uma scope function. O objetivo dela é alterar os atributos de um objeto sem a necessidade
        de chamar o objeto em questão para cada atributo que desejamos alterar

        As scope functions são:

        let - executa uma ação em um objeto e retorna o resultado da expressão lambda;

        run - também executa uma ação em um objeto como o let, mas não retorna o resultado da expressão, e sim o resultado do bloco de código;

        with - executa uma sequência de operações sendo necessário passar o objeto como argumento explícito;

        apply - realiza operações de configuração em um objeto e retorna o próprio objeto modificado;

        also - realiza a mesma coisa que o apply, porém retorna o próprio objeto original.
    */
    gamer1.let {
        it.dataNascimento = "02/02/2000"
        it.usuario = "Username2"
    }.also {
        println(gamer1.idInterno)
    }
}
