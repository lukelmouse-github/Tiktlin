package com.qxy.tiktlin.common.network.config

import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl

// 持久化 cookie
class LocalCookieJar : CookieJar{
    // cookie 的本地化存储
    private val cache = mutableListOf<Cookie>()
    override fun loadForRequest(url: HttpUrl): List<Cookie> {
        // 过期 cookie
        val invalidCookies: MutableList<Cookie> = ArrayList()

        // 有效 cookie
        val validCookie: MutableList<Cookie> = ArrayList()

        for (cookie in cache) {
            // 判断是否过期
            if (cookie.expiresAt < System.currentTimeMillis()) {
                invalidCookies.add(cookie)
            } else if (cookie.matches(url)) {
                validCookie.add(cookie)
            }
        }

        // 移除过期 cookie
        cache.removeAll(invalidCookies)

        return validCookie
    }

    override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
        cache.addAll(cookies)
    }
}