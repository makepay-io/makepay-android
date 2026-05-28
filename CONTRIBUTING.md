# Contributing

Thanks for helping improve the MakePay Android SDK.

## Development

```bash
gradle --no-daemon testReleaseUnitTest lintRelease assembleRelease
```

## Pull Requests

- Keep partner-secret functionality out of the Android SDK.
- Add tests for URL builders, mobile request JSON, and return/deep-link parsing.
- Update the README when changing checkout behavior or backend request contracts.
- Keep the public API compatible unless the change is intentionally breaking.
