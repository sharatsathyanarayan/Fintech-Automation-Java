# Fintech Automation Framework (Java + Selenium + REST Assured)

Covers all required points:
- **Java & Selenium** for UI; **REST Assured** for API.
- **API & UI examples**, **data factories**, **reporting (Allure)**, **screenshots on UI failures**, **API logging**.
- **Env configs** via `Owner` (`dev`, `qa`, `prod`).
- **Mock APIs** using **WireMock**.

## Run

```bash
mvn clean test -Denv=dev
mvn allure:report
mvn allure:serve
```

- UI points to a local mock page in `src/test/resources/ui/mock/index.html` and calls `http://localhost:8089` (WireMock).

## Structure

```
src/test/java/com/example/fintech/
  api/            -> API tests
  ui/             -> Selenium tests
  data/           -> Test data factories
  http/           -> REST Assured base
  config/         -> Owner config
  util/           -> Helpers, listener, custom assertions
  wiremock/       -> Embedded mock server + stubs
src/test/resources/
  env/            -> dev/qa/prod props
  ui/mock/        -> Minimal frontend (HTML+JS)
```

## Filters

- Run only API: `mvn -Dtest=*ApiTests test`
- Run only UI:  `mvn -Dtest=*UiTests test`
