package br.com.magna.alugames.br.com.magna.alugames.modelo

data class InfoJogo(
    val info: InfoApiCheapShark
) {

    override fun toString(): String {
        return info.toString()
    }
}