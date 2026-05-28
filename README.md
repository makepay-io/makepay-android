# MakePay Android SDK

Android Kotlin helpers for MakePay checkout flows.

This SDK is intentionally mobile-safe: it does not accept or store MakePay partner API secrets. Android apps should request payment-link creation from their own backend, then use this SDK to open hosted checkout, embedded checkout, donation links, and parse return deep links.

## Requirements

- Android Gradle Plugin `9.2.1`
- Kotlin `2.3.21`
- Android compile SDK `36`
- Minimum SDK `23`

## Installation

The package is planned for Maven Central as:

```kotlin
implementation("io.makepay:makepay-android:0.1.0")
```

Until the first release is published, include the `makepay-android` module or publish it to your internal Maven registry.

## Checkout

```kotlin
val checkout = MakePayCheckout()

startActivity(checkout.checkoutIntent("pay_123"))
```

Use Custom Tabs:

```kotlin
checkout.launchCheckout(context, "pay_123")
```

Build URLs without launching UI:

```kotlin
val hosted = MakePayCheckoutUrls.hostedPaymentUrl("pay_123")
val embedded = MakePayCheckoutUrls.embeddedPaymentUrl(
    paymentUid = "pay_123",
    parentOrigin = "https://merchant.example",
)
```

## Backend Relay Payloads

Payment links must be created by a trusted backend with MakePay credentials. The Android app can send a mobile-safe request body to that backend:

```kotlin
val body = MakePayMobileJson.encodePaymentLinkRequest(
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
```

Your backend should validate the cart/order server-side, create the MakePay payment link with the partner API, and return the payment UID or hosted checkout URL to the app.

## Deep Link Results

```kotlin
val result = MakePayCheckoutResultParser.parse(intent.data!!)
```

The parser reads common return parameters such as `status`, `paymentUid`, and `orderId`.

## Validation

```bash
gradle --no-daemon testReleaseUnitTest lintRelease assembleRelease
```

Maintainer: Ethan Carter (`makepayio`).
