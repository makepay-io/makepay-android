package io.makepay.android

import java.net.URI
import java.net.URLEncoder

public object MakePayCheckoutUrls {
    public fun hostedPaymentUrl(
        paymentUid: String,
        checkoutBaseUrl: String = "https://makepay.io",
    ): String {
        return buildUri(checkoutBaseUrl, "/payment/${escape(paymentUid, "paymentUid")}").toString()
    }

    public fun embeddedPaymentUrl(
        paymentUid: String,
        checkoutBaseUrl: String = "https://makepay.io",
        parentOrigin: String? = null,
    ): String {
        val query = if (parentOrigin == null) emptyMap() else mapOf("parentOrigin" to parentOrigin)
        return buildUri(checkoutBaseUrl, "/embed/payment/${escape(paymentUid, "paymentUid")}", query).toString()
    }

    public fun hostedDonationUrl(
        donationSlug: String,
        checkoutBaseUrl: String = "https://makepay.io",
    ): String {
        return buildUri(checkoutBaseUrl, "/donations/${escape(donationSlug, "donationSlug")}").toString()
    }

    private fun buildUri(
        checkoutBaseUrl: String,
        path: String,
        query: Map<String, String?> = emptyMap(),
    ): URI {
        val resolved = URI(checkoutBaseUrl.trimEnd('/') + "/").resolve(path.trimStart('/'))
        val queryString = query
            .mapNotNull { (key, value) ->
                if (value == null) null else "${encode(key)}=${encode(value)}"
            }
            .joinToString("&")

        if (queryString.isBlank()) {
            return resolved
        }

        return URI.create("$resolved?$queryString")
    }

    private fun escape(value: String, parameterName: String): String {
        require(value.isNotBlank()) { "$parameterName is required." }
        return encode(value)
    }

    private fun encode(value: String): String {
        @Suppress("DEPRECATION")
        return URLEncoder.encode(value, "UTF-8").replace("+", "%20")
    }
}
