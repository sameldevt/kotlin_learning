package br.com.magna.orgs.ui.activity

import android.app.Activity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.magna.orgs.R
import br.com.magna.orgs.model.Produto
import br.com.magna.orgs.recyclerview.adapter.ListaProdutoAdapter
import java.math.BigDecimal

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        recyclerView.adapter = ListaProdutoAdapter(
            context = this,
            produtos = listOf(
                Produto(
                    nome = "teste",
                    descricao = "teste desc",
                    valor = BigDecimal("19.99")
                ),
                Produto(
                    nome = "teste2",
                    descricao = "teste desc2",
                    valor = BigDecimal("10.99")
                ),
                Produto(
                    nome = "teste3",
                    descricao = "teste desc3",
                    valor = BigDecimal("29.99")
                )
            )
        )
    }
}

