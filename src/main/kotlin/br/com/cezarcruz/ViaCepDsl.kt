package br.com.cezarcruz

import br.com.cezarcruz.cliente.ClienteViaCep
import br.com.cezarcruz.dominio.Endereco

class ViaCepDsl(
    var cep: String = "",
    var uf: String = ""
) {
    fun build(): Endereco {

        if (cep.isBlank() && uf.isBlank()) {
            throw RuntimeException("forneca parametros")
        }

        return ClienteViaCep().obtemCep(cep = cep)
    }
}

fun viacep(lambda: ViaCepDsl.() -> Unit) = ViaCepDsl().apply(lambda)


