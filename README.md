# ace-common

Shared utilities library for ACE microservices. Provides common exceptions, helpers, payloads, and utilities used across all services.

## Usage

Add the following to your service's `build.gradle`:

### 1. Add JitPack repository

```groovy
repositories {
    mavenCentral()
    maven { url 'https://jitpack.io' }
}
```

### 2. Add the dependency

```groovy
dependencies {
    implementation 'com.github.phathh93:ace-common:1.0.0'
}
```

> Replace `YOUR_GITHUB_USERNAME` with the actual GitHub username/org and `1.0.0` with the desired [release tag](../../releases).

## What's Included

| Package | Description |
|---|---|
| `com.ace.util` | General utilities (`Utils`, `ValidateHelper`, `TransactionTypeConverter`) |
| `com.ace.exception` | Global REST exception handler (`RestExceptionHandler`) |
| `com.ace.payload` | Shared response payloads (`AceErrorPayload`) |

## Releasing a New Version

1. Bump `version` in `build.gradle`
2. Commit and push
3. Create a GitHub Release with a tag matching the version (e.g. `1.0.1`)
4. JitPack will build it automatically on first request

## Requirements

- Java 17+
- Spring Boot 2.7.x (provided — not bundled)
