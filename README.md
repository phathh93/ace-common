# ace-common

**Type**: Shared Java library (no Spring Boot — not a runnable service)  
**Responsibility**: Shared classes used by all microservices.

## Contents
- `AceConst.java` — route constants, role names, common values
- `AceStatusCode.java` — standard API response codes
- `MessageKeys.java` — i18n message keys
- `enums/` — shared enums (game type, payment status, etc.)
- `crypto/` — cryptography utilities
- `util/` — string, date, number utilities
- `exception/` — base exception classes
- `annotations/` — custom Spring annotations

## Build

All microservices depend on this JAR. **Build it first** before running any service:

```bash
# From /Users/phath/Work/portal-microservice/ace-common
./gradlew jar

# Output: build/libs/ace-common-0.0.1-SNAPSHOT.jar
# All services reference it via: implementation files('../ace-common/build/libs/ace-common-0.0.1-SNAPSHOT.jar')
```

## When to rebuild

Rebuild whenever you change anything in `ace-common`, then restart the affected services.
