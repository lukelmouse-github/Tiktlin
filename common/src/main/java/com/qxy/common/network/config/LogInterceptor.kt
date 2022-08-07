package com.qxy.common.network.config

import android.util.Log
import com.qxy.common.network.support.TikUtils
import okhttp3.Connection
import okhttp3.Interceptor
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.Response
import java.net.URLDecoder
import java.text.SimpleDateFormat
import java.util.*
import okio.Buffer

// Log 拦截器
class LogInterceptor(block: (LogInterceptor.() -> Unit)? = null) : Interceptor {
    private var logLevel: LogLevel = LogLevel.NONE
    private var colorLevel: ColorLevel = ColorLevel.DEBUG
    private var logTag = TAG

    init {
        block?.invoke(this)
    }

    // 日志范围
    enum class LogLevel {
        NONE, // 不打印
        BASIC, // 只打印行首的请求和响应
        HEADERS, // 打印请求和响应的所有header
        BODY, // 打印所有
    }

    // Log 颜色等级
    enum class ColorLevel {
        VERBOSE,
        DEBUG,
        INFO,
        WARN,
        ERROR
    }

    fun logLevel(level: LogLevel): LogInterceptor {
        logLevel = level
        return this
    }

    fun colorLevel(level: ColorLevel): LogInterceptor {
        colorLevel = level
        return this
    }

    fun logTag(tag: String): LogInterceptor {
        logTag = tag
        return this
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        return kotlin.runCatching { chain.proceed(request) }
            .onFailure { error ->
                error.printStackTrace()
                logIt(
                    error.message.toString(),
                    ColorLevel.ERROR
                )
            }.onSuccess { response ->
                if (logLevel == LogLevel.NONE) {
                    return response
                }
                logRequest(request, chain.connection())
                logResponse(response)
            }.getOrThrow()
    }

    companion object {
        private const val TAG = "<Log>"

        const val MILLIS_PATTERN = "yyyy-MM-dd HH:mm:ss.SSSXXX"

        // 转化为格式化的时间字符串
        fun toDateTimeStr(millis: Long, pattern: String): String {
            return SimpleDateFormat(pattern, Locale.getDefault()).format(millis)
        }
    }

    private fun logIt(any: Any, tempLevel: ColorLevel? = null) {
        when (tempLevel ?: colorLevel) {
            ColorLevel.VERBOSE -> Log.v(logTag, any.toString())
            ColorLevel.DEBUG -> Log.d(logTag, any.toString())
            ColorLevel.INFO -> Log.i(logTag, any.toString())
            ColorLevel.WARN -> Log.w(logTag, any.toString())
            ColorLevel.ERROR -> Log.e(logTag, any.toString())
        }
    }

    private fun logBasicRsp(response: Response, sb: StringBuffer) {
        sb.appendLine("响应 protocol: ${response.protocol} code: ${response.code} message: ${response.message}")
            .appendLine("响应 request URL: ${decodeUrlStr(response.request.url.toString())}")
            .appendLine(
                "响应 sentRequestTime: ${
                    toDateTimeStr(
                        response.sentRequestAtMillis,
                        MILLIS_PATTERN
                    )
                } receivedResponseTime: ${
                    toDateTimeStr(
                        response.receivedResponseAtMillis,
                        MILLIS_PATTERN
                    )
                }"
            )
    }

    private fun logHeadersRsp(response: Response, sb: StringBuffer) {
        logBasicRsp(response, sb)
        val headerStr = response.headers.joinToString(separator = "") { header ->
            "响应 Header: {${header.first}=${header.second}}\n"
        }
        sb.appendLine(headerStr)
    }

    // 记录响应日志
    private fun logResponse(response: Response) {
        val sb = StringBuffer()
        sb.appendLine("\r\n")
        sb.appendLine("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<")
        when (logLevel) {
            LogLevel.NONE -> {

            }
            LogLevel.BASIC -> {
                logBasicRsp(response, sb)
            }
            LogLevel.HEADERS -> {
                logHeadersRsp(response, sb)
            }
            LogLevel.BODY -> {
                logHeadersRsp(response, sb)
                kotlin.runCatching {
                    val peekBody = response.peekBody(1024 * 1024)
                    // Todo 待确认，函数可能出错
                    sb.appendLine(TikUtils.unicodeDecode(peekBody.toString()))
                }.getOrNull()
            }
        }
        sb.appendLine("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<")
        logIt(sb, ColorLevel.INFO)
    }

    private fun logBasicReq(
        request: Request,
        sb: StringBuffer,
        connection: Connection?
    ) {
        sb.appendLine("""
            请求 method: ${request.method} url: ${decodeUrlStr(request.url.toString())} 
            tag: ${request.tag()} protocol: ${connection?.protocol() ?: Protocol.HTTP_1_1}
        """)
    }

    private fun logHeadersReq(
        request: Request,
        sb: StringBuffer,
        connection: Connection?
    ) {
        logBasicReq(request, sb, connection)
        val headersStr = request.headers.joinToString("") { header ->
            "请求 Header: {${header.first}=${header.second}}\n"
        }
        sb.appendLine(headersStr)
    }

    private fun logBodyReq(
        request: Request,
        sb: StringBuffer,
        connection: Connection?
    ) {
        logHeadersReq(request, sb, connection)
        val req = request.newBuilder().build()
        val sink = Buffer()
        req.body?.writeTo(sink)
        sb.appendLine("RequestBody: ${sink.readUtf8()}")
    }

    private fun logRequest(request: Request, connection: Connection?) {
        val sb = StringBuffer()
        sb.appendLine("\r\n")
        sb.appendLine(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>")
        when (logLevel) {
            LogLevel.NONE -> {

            }
            LogLevel.BASIC -> {
                logBasicReq(request, sb, connection)
            }
            LogLevel.HEADERS -> {
                logHeadersReq(request, sb, connection)
            }
            LogLevel.BODY -> {
                logBodyReq(request, sb, connection)
            }
        }
        sb.appendLine(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>")
        logIt(sb)
    }



    // url 解码
    private fun decodeUrlStr(url: String): String? {
        return kotlin.runCatching {
            URLDecoder.decode(url, "utf-8")
        }.onFailure { it.printStackTrace() }.getOrNull()
    }
}