package br.com.cezarcruz

import br.com.cezarcruz.cliente.ClienteViaCep
import br.com.cezarcruz.dominio.Endereco
import br.com.cezarcruz.utils.CepUtils

object ViaCep {

    fun consultaPorCep(cep: String): Endereco {
        val cepLimpo = CepUtils.limpa(cep)
        return ClienteViaCep().obtemCep(cepLimpo)
    }

    fun consultaPor(uf: String, cidade: String, vararg rua: String): MutableList<Endereco> {
        return ClienteViaCep().obtemCepPor(uf, cidade, rua.joinToString(separator = "+"))
    }

}