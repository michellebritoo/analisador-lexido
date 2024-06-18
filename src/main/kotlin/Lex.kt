enum class TokenType {
    DEFINICAO_FUNCAO,
    IDENTIFICADOR,
    DELIMITADOR,
    ATRIBUICAO,
    OPERADOR_MATEMATICO,
    SEPARADOR
}

data class Token(val type: TokenType, val value: String, val position: Int)

fun lex(input: String): List<Token> {
    val tokens = mutableListOf<Token>()
    val lines = input.split("\n")

    for ((index, line) in lines.withIndex()) {
        val lineNumber = index + 1

        // Match function definition
        Regex("fun\\s+(\\w+)").find(line)?.let {
            tokens.add(Token(TokenType.DEFINICAO_FUNCAO, it.groupValues[1], lineNumber))
        }

        // Match identifiers
        Regex("(\\w+)").findAll(line).forEach {
            tokens.add(Token(TokenType.IDENTIFICADOR, it.groupValues[1], lineNumber))
        }

        // Match delimiters
        Regex("(\\{|\\})").findAll(line).forEach {
            tokens.add(Token(TokenType.DELIMITADOR, it.groupValues[1], lineNumber))
        }

        // Match assignment
        Regex("(=)").find(line)?.let {
            tokens.add(Token(TokenType.ATRIBUICAO, it.groupValues[1], lineNumber))
        }

        // Match mathematical operators
        Regex("(\\+|-|\\*|/)").findAll(line).forEach {
            tokens.add(Token(TokenType.OPERADOR_MATEMATICO, it.groupValues[1], lineNumber))
        }

        // Match separators
        Regex("(;)").find(line)?.let {
            tokens.add(Token(TokenType.SEPARADOR, it.groupValues[1], lineNumber))
        }
    }

    return tokens
}