package io.makepay.android

import org.junit.Assert.assertEquals
import org.junit.Test

class MakePayCheckoutUrlsTest {
    @Test
    fun hostedPaymentUrlUsesConfiguredCheckoutHost() {
        val url = MakePayCheckoutUrls.hostedPaymentUrl(
            paymentUid = "pay_123",
            checkoutBaseUrl = "https://checkout.example/",
        )

        assertEquals("https://checkout.example/payment/pay_123", url)
    }

    @Test
    fun embeddedPaymentUrlIncludesParentOrigin() {
        val url = MakePayCheckoutUrls.embeddedPaymentUrl(
            paymentUid = "pay_123",
            checkoutBaseUrl = "https://checkout.example",
            parentOrigin = "https://merchant.example",
        )

        assertEquals(
            "https://checkout.example/embed/payment/pay_123?parentOrigin=https%3A%2F%2Fmerchant.example",
            url,
        )
    }

    @Test(expected = IllegalArgumentException::class)
    fun hostedPaymentUrlRejectsBlankPaymentUid() {
        MakePayCheckoutUrls.hostedPaymentUrl(" ")
    }
}
