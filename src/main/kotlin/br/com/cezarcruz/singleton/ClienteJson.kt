package br.com.cezarcruz.singleton

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.kotlinModule

object ClienteJson {
    fun jackson(): ObjectMapper = ObjectMapper().registerModule(kotlinModule())
}