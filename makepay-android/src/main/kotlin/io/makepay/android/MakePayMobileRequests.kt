package io.makepay.android

import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject

@Serializable
public data class MakePayMobilePaymentLinkPayload public constructor(
    public val title: String? = null,
    public val label: String? = null,
    public val description: String? = null,
    public val amount: String? = null,
    public val currency: String? = null,
    public val asset: String? = null,
    public val orderId: String? = null,
    public val customerEmail: String? = null,
    public val returnUrl: String? = null,
    public val successUrl: String? = null,
    public val failureUrl: String? = null,
    public val metadata: JsonObject? = null,
)

@Serializable
public data class MakePayMobilePaymentLinkRequest public constructor(
    public val payload: MakePayMobilePaymentLinkPayload,
    public val clientReferenceId: String? = null,
    public val sendPaymentRequestEmail: Boolean = false,
)

public object MakePayMobileJson {
    public val json: Json = Json {
        encodeDefaults = false
        ignoreUnknownKeys = true
    }

    public fun encodePaymentLinkRequest(request: MakePayMobilePaymentLinkRequest): String {
        return json.encodeToString(request)
    }

    public fun decodePaymentLinkRequest(payload: String): MakePayMobilePaymentLinkRequest {
        return json.decodeFromString(payload)
    }
}
