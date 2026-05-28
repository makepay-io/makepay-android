package io.makepay.android

import android.net.Uri

public data class MakePayCheckoutResult public constructor(
    public val status: String? = null,
    public val paymentUid: String? = null,
    public val orderId: String? = null,
)

public object MakePayCheckoutResultParser {
    public fun parse(uri: Uri): MakePayCheckoutResult {
        return MakePayCheckoutResult(
            status = uri.getQueryParameter("status"),
            paymentUid = uri.getQueryParameter("paymentUid") ?: uri.getQueryParameter("payment_uid"),
            orderId = uri.getQueryParameter("orderId") ?: uri.getQueryParameter("order_id"),
        )
    }
}
