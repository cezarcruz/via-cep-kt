package br.com.cezarcruz.utils

object CepUtils {
    fun limpa(cep: String): String {
        return cep.filter { it.isDigit() }
    }
}