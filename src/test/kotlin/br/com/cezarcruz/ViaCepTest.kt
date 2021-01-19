package br.com.cezarcruz

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.lang.RuntimeException
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.fail

class ViaCepTest {

    @Test
    fun `deve buscar cep valido`() {
        val endereco = ViaCep.consultaPorCep("01311200")

        assertNotNull(endereco)

        assertEquals("01311-200", endereco.cep)
        assertEquals("Avenida Paulista", endereco.logradouro)
        assertEquals("de 1047 a 1865 - lado ímpar", endereco.complemento)
        assertEquals("Bela Vista", endereco.bairro)
        assertEquals("São Paulo", endereco.localidade)
        assertEquals("SP", endereco.uf)
    }

    @Test
    fun `deve tentar buscar cep invalido`() {
        Assertions.assertThrows(RuntimeException::class.java) {
            ViaCep.consultaPorCep("123456")
        }
    }

    @Test
    fun `deve buscar cep por uf, cidade e rua`() {
        val listaEnderecos = ViaCep.consultaPor("rs", "Porto%20Alegre", "Domingos Jose")

        assertEquals(2, listaEnderecos.size)

        val josePoli = listaEnderecos.get(0)

        assertEquals("91790-072", josePoli.cep)
        assertEquals("Rua Domingos José Poli", josePoli.logradouro)
        assertEquals("", josePoli.complemento)
        assertEquals("Restinga", josePoli.bairro)
        assertEquals("Porto Alegre", josePoli.localidade)
        assertEquals("RS", josePoli.uf)

        val joseAlmeida = listaEnderecos.get(1)

        assertEquals("90420-200", joseAlmeida.cep)
        assertEquals("Rua Domingos José de Almeida", joseAlmeida.logradouro)
        assertEquals("", joseAlmeida.complemento)
        assertEquals("Rio Branco", joseAlmeida.bairro)
        assertEquals("Porto Alegre", joseAlmeida.localidade)
        assertEquals("RS", joseAlmeida.uf)
    }

    @Test
    fun `deve buscar cep por uf, cidade e rua composta`() {
        val listaEnderecos = ViaCep.consultaPor("rs", "Porto Alegre", "Domingos", "Jose")

        assertEquals(3, listaEnderecos.size)

        val josePoli = listaEnderecos.get(0)

        assertEquals("91790-072", josePoli.cep)
        assertEquals("Rua Domingos José Poli", josePoli.logradouro)
        assertEquals("", josePoli.complemento)
        assertEquals("Restinga", josePoli.bairro)
        assertEquals("Porto Alegre", josePoli.localidade)
        assertEquals("RS", josePoli.uf)

        val joseVarela = listaEnderecos.get(1)

        assertEquals("91910-420", joseVarela.cep)
        assertEquals("Rua José Domingos Varella", joseVarela.logradouro)
        assertEquals("", joseVarela.complemento)
        assertEquals("Cavalhada", joseVarela.bairro)
        assertEquals("Porto Alegre", joseVarela.localidade)
        assertEquals("RS", joseVarela.uf)

        val joseAlmeida = listaEnderecos.get(2)

        assertEquals("90420-200", joseAlmeida.cep)
        assertEquals("Rua Domingos José de Almeida", joseAlmeida.logradouro)
        assertEquals("", joseAlmeida.complemento)
        assertEquals("Rio Branco", joseAlmeida.bairro)
        assertEquals("Porto Alegre", joseAlmeida.localidade)
        assertEquals("RS", joseAlmeida.uf)
    }

}