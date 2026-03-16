# AGENTS.md

## Project Snapshot
- Kotlin + Spring Boot REST template with a small server-rendered landing page.
- Main app entrypoint: `src/main/kotlin/SmartRestApplication.kt` (logs version and runtime URL at startup).
- Core domain is `Person` + `Phone`, persisted with Spring Data JPA on in-memory H2.

## Architecture and Data Flow
- REST boundary: `src/main/kotlin/net/littlelite/smartrest/controller/rest/PersonController.kt` under `/api/v1/persons`.
- Controller delegates all business logic to `PersonService`; avoid putting persistence logic in controllers.
- Service layer: `src/main/kotlin/net/littlelite/smartrest/service/PersonService.kt`.
- Persistence layer: `PersonDAO` / `PhoneDAO` (`CrudRepository`), with query methods derived from names.
- DTO mapping pattern:
  - input DTO `NewPersonDTO.toPerson()`
  - output DTO `PersonDTO.fromPerson(...)` (includes phone flattening + date formatting)
- Startup data seeding: `DbInitializer` (`ApplicationRunner`) inserts 4 people and shared phone records.

## Runtime/Behavior Details That Affect Changes
- DB lifecycle is reset each run (`ddl-auto: create-drop`) in `src/main/resources/application.yml`.
- Tests and local assumptions depend on seeded data count (4 persons).
- H2 console is enabled at `/h2`; landing page template links to `/swagger-ui/index.html` and `/h2`.
- CORS is globally permissive (`allowedMethods("*")`) in `WebSecurity`.
- Custom exceptions are thrown from controller methods (`ResourceNotFound`, `ResourceAlreadyExists`) without a dedicated exception handler class.

## Build, Test, Run Workflows
- Prefer Gradle wrapper from repo root:
  - `./gradlew clean build`
  - `./gradlew test`
  - `./gradlew bootRun`
- Current observed state: `./gradlew test` fails to compile tests due nullable `id` usage in `src/test/kotlin/net/littlelite/smartrest/PersonTests.kt`.
- Docker image build follows README pattern (jar then image):
  - `./gradlew clean assemble`
  - `docker build --platform linux/amd64 --build-arg JAR_FILE=build/libs/SmartRest.jar -t smartrest:1.0 .`
- Kubernetes deploy path is Helm chart in `helm/` (`service.type: NodePort`, container port `8080`).

## Codebase Conventions and Pitfalls
- When adding new files, be sure to put the header with copyright information, copying it from existing files 
- Package root is generally `net.littlelite.smartrest` (keep new files aligned with this tree).
- Two existing files use duplicated package prefixes:
  - `src/main/kotlin/net/littlelite/smartrest/controller/web/WebController.kt`
  - `src/main/kotlin/net/littlelite/smartrest/config/WebSecurity.kt`
  Match existing package declarations when editing those files unless you refactor imports project-wide.
- Entity relation pattern: `Person` owns lifecycle of phones (`@OneToMany(cascade = ALL, fetch = EAGER, mappedBy = "person")`) and sets reverse link in `addPhone`.
- Keep API contract style consistent: collection endpoints return DTO lists; create endpoint returns `201` with `Location` header only.

## Integration Points
- Spring starters used: webmvc, data-jpa, freemarker, hateoas, H2 console (`build.gradle.kts`).
- Helm templates under `helm/templates/` define deployment/service/optional ingress and connection test pod.

