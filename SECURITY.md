# Security Policy

## Supported Versions

The `main` branch receives security fixes until versioned releases begin.

## Reporting a Vulnerability

Email security reports to `info@makepay.io`.

Please include affected APIs, reproduction steps, expected impact, and any request or response examples with secrets removed.

## Secret Handling

- Never put MakePay partner API keys or webhook secrets into Android apps.
- Create payment links on your own backend and return only checkout-safe data to mobile clients.
- Validate mobile cart/order details on the backend before creating MakePay resources.
- Treat deep-link return parameters as untrusted hints and reconcile payment status server-side.
