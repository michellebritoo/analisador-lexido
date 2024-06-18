import java.io.File

var content = ""

fun main() {
    readCode()
    lexCode()
}

private fun readCode() {
    val filePath = "input.txt"

    val file = File(filePath)
    if (!file.exists()) {
        file.createNewFile()
        println("Arquivo 'input.txt' criado.")
    }

    content = file.readText()
    println("Conteúdo do arquivo: \n$content")
}

private fun lexCode() {

    val tokens = lex(content.trimIndent())

    for (token in tokens) {
        println("Posição linha ${token.position}: classe ${token.type}, lexema ${token.value}")
    }
}