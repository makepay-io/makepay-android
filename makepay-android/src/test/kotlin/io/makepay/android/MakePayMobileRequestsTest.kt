package io.makepay.android

import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Test

class MakePayMobileRequestsTest {
    @Test
    fun encodesPaymentLinkRequestWithoutSecrets() {
        val encoded = MakePayMobileJson.encodePaymentLinkRequest(
            MakePayMobilePaymentLinkRequest(
                clientReferenceId = "cart_123",
                payload = MakePayMobilePaymentLinkPayload(
                    title = "Order #1042",
                    amount = "129.99",
                    currency = "USDT",
                    customerEmail = "buyer@example.com",
                ),
            ),
        )

        val decoded = MakePayMobileJson.json.parseToJsonElement(encoded).jsonObject
        assertEquals("cart_123", decoded["clientReferenceId"]!!.jsonPrimitive.content)
        assertEquals("Order #1042", decoded["payload"]!!.jsonObject["title"]!!.jsonPrimitive.content)
        assertFalse(encoded.contains("keySecret"))
        assertFalse(encoded.contains("webhookSecret"))
    }
}
