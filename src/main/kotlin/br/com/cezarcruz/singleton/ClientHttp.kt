package br.com.cezarcruz.singleton

import okhttp3.OkHttpClient

object ClientHttp {
    fun okHttp() = OkHttpClient()
}