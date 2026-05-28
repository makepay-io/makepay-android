package io.makepay.android

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.net.toUri

public class MakePayCheckout public constructor(
    private val config: MakePayCheckoutConfig = MakePayCheckoutConfig(),
) {
    public fun hostedPaymentUri(paymentUid: String): Uri {
        return MakePayCheckoutUrls.hostedPaymentUrl(paymentUid, config.checkoutBaseUrl).toUri()
    }

    public fun embeddedPaymentUri(paymentUid: String, parentOrigin: String? = null): Uri {
        return MakePayCheckoutUrls.embeddedPaymentUrl(paymentUid, config.checkoutBaseUrl, parentOrigin).toUri()
    }

    public fun hostedDonationUri(donationSlug: String): Uri {
        return MakePayCheckoutUrls.hostedDonationUrl(donationSlug, config.checkoutBaseUrl).toUri()
    }

    public fun checkoutIntent(paymentUid: String): Intent {
        return Intent(Intent.ACTION_VIEW, hostedPaymentUri(paymentUid))
            .addCategory(Intent.CATEGORY_BROWSABLE)
    }

    public fun donationIntent(donationSlug: String): Intent {
        return Intent(Intent.ACTION_VIEW, hostedDonationUri(donationSlug))
            .addCategory(Intent.CATEGORY_BROWSABLE)
    }

    public fun customTabsIntent(paymentUid: String): CustomTabsIntent {
        return CustomTabsIntent.Builder()
            .build()
            .also { intent -> intent.intent.data = hostedPaymentUri(paymentUid) }
    }

    public fun launchCheckout(context: Context, paymentUid: String) {
        customTabsIntent(paymentUid).launchUrl(context, hostedPaymentUri(paymentUid))
    }
}
