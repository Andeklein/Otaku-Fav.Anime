package com.andre.otakufav_anime

import java.net.URLDecoder

class Utils {

    companion object {
        fun extractImageUrl(url: String): String? {
            if (url.endsWith(".jpg"))
                return url
            val regex = "imgurl=([^&]*)".toRegex()
            val matchResult = regex.find(url)
            return matchResult?.groups?.get(1)?.value?.let { URLDecoder.decode(it, "UTF-8") } }
    }
}