package br.com.cezarcruz

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class ViaCepDslTest {

    @Test
    fun `deve buscar por um cep valido`() {
        val endereco = viacep {
            cep = "01311-200"
        }.build()

        assertNotNull(endereco)

        assertEquals("01311-200", endereco.cep)
        assertEquals("Avenida Paulista", endereco.logradouro)
        assertEquals("de 1047 a 1865 - lado ímpar", endereco.complemento)
        assertEquals("Bela Vista", endereco.bairro)
        assertEquals("São Paulo", endereco.localidade)
        assertEquals("SP", endereco.uf)
    }

}