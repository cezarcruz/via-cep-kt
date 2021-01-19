package br.com.cezarcruz.cliente

import br.com.cezarcruz.dominio.Endereco
import br.com.cezarcruz.singleton.ClientHttp
import br.com.cezarcruz.singleton.ClienteJson
import okhttp3.Request
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class ClienteViaCep {

    private val log: Logger = LoggerFactory.getLogger(ClienteViaCep::class.java)

    fun obtemCep(cep: String): Endereco {
        log.info("buscando cep: $cep")
        val url = "https://viacep.com.br/ws/$cep/json/"
        log.debug("url gerada: $url")

        val build = Request.Builder().url(url).build()

        ClientHttp.okHttp().newCall(build).execute().run {
            return if (this.isSuccessful) {
                ClienteJson.jackson().readValue(this.body().string(), Endereco::class.java)
            } else {
                log.error("o cep buscado é invalido")
                throw RuntimeException("cep buscado invalido: $cep")
            }

        }

    }

    fun obtemCepPor(uf: String, cidade: String, rua: String): MutableList<Endereco> {
        log.info("buscando cep: $uf, $cidade, $rua")

        val url = "https://viacep.com.br/ws/$uf/$cidade/${rua}/json/";

        val request = Request.Builder().url(url).build()

        ClientHttp.okHttp().newCall(request).execute().run {
            return if (this.isSuccessful) {
                ClienteJson.jackson().readValue(
                    this.body().string(),
                    ClienteJson.jackson().typeFactory.constructCollectionType(
                        MutableList::class.java,
                        Endereco::class.java
                    )
                )
            } else {
                log.error("parametros de busca invalido")
                throw RuntimeException("parametros de busca invalido")
            }
        }
    }

}