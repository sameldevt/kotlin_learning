package br.com.magna.alugames.br.com.magna.alugames.modelo

/*  var -> pode ser reatribuido
    val -> não pode ser reatribuido

    @SerializedName é uma anotação da lib Gson que indica qual o nome
    do atributo no arquivo Json retornado por uma requisição via API

    import com.google.gson.annotations.SerializedName

    @SerializedName("title") val titulo: String,
    @SerializedName("thumb") val capa: String,

*/

data class Jogo(
    val titulo: String,
    val capa: String
) {
    var descricao:String? = null

    override fun toString(): String {
        return "Jogo(titulo='$titulo'\ncapa='$capa'\ndescricao='$descricao')"
    }
}