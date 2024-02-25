package com.example.kopringworkshop.logback.config

import jakarta.servlet.ReadListener
import jakarta.servlet.ServletInputStream
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletRequestWrapper
import org.springframework.util.StreamUtils
import java.io.BufferedReader
import java.io.ByteArrayInputStream
import java.io.InputStream
import java.io.InputStreamReader

class CachedBodyHttpServletRequest(request: HttpServletRequest): HttpServletRequestWrapper(request) {
    private val cachedBody: ByteArray

    init {
        val requestInputStream: InputStream = request.inputStream
        this.cachedBody = StreamUtils.copyToByteArray(requestInputStream)
    }

    override fun getInputStream(): ServletInputStream {
        return CachedBodyServletInputStream(this.cachedBody)
    }

    override fun getReader(): BufferedReader {
        val byteArrayInputStream = ByteArrayInputStream(this.cachedBody)
        return BufferedReader(InputStreamReader(byteArrayInputStream))
    }

    private class CachedBodyServletInputStream(cachedBody: ByteArray): ServletInputStream() {
        private val cachedBodyInputStream: InputStream = ByteArrayInputStream(cachedBody)

        override fun isFinished(): Boolean {
            return cachedBodyInputStream.available() == 0
        }

        override fun isReady(): Boolean {
            return true
        }

        override fun setReadListener(readListener: ReadListener) {
            throw UnsupportedOperationException()
        }

        override fun read(): Int {
            return cachedBodyInputStream.read()
        }
    }
}
