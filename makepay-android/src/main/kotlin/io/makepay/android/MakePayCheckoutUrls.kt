package io.makepay.android

import java.net.URI
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

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

        return URI(
            resolved.scheme,
            resolved.authority,
            resolved.path,
            queryString.ifBlank { null },
            null,
        )
    }

    private fun escape(value: String, parameterName: String): String {
        require(value.isNotBlank()) { "$parameterName is required." }
        return encode(value)
    }

    private fun encode(value: String): String {
        return URLEncoder.encode(value, StandardCharsets.UTF_8).replace("+", "%20")
    }
}
