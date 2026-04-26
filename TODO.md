# Day 10 & 11 Implementation TODO

## Day 10 — Core Feature Development
- [x] Analyze project structure and identify missing pieces
- [x] Create `backend/pom.xml`
- [x] Create `backend/src/main/resources/application.yml`
- [x] Create `Policy.java` entity
- [x] Create `User.java` entity
- [x] Create `UserRepository.java`
- [x] Create `JwtUtil.java`
- [x] Create `JwtAuthenticationFilter.java`
- [x] Update `SecurityConfig.java`
- [x] Update `PolicyController.java` (add GET /all, POST /create, update soft delete)
- [x] Update `PolicyRepository.java`
- [x] Update `V1__init.sql`
- [x] Create `V4__seed_data.sql` (30 demo records)
- [x] Create `PolicyControllerIntegrationTest.java`
- [x] Update `SecurityAuditTest.java`
- [x] Create `docker-compose.yml`
- [x] Create `Dockerfile`
- [x] Create `V5__performance_indexes.sql`

## Day 11 — Integration Testing & Coverage (80% JaCoCo)
- [x] Add Testcontainers dependencies to `pom.xml`
- [x] Add AssertJ dependency
- [x] Add JaCoCo plugin with 80% threshold
- [x] Create `FullSystemIntegrationTest.java` (Testcontainers + PostgreSQL + full CRUD + audit log)
- [x] Create `PolicyRepositoryTest.java` (@DataJpaTest with custom queries, pagination, counts)
- [x] Create `JwtUtilTest.java` (unit tests for JWT generation/validation)
- [x] Create `AuthControllerTest.java` (register, login, refresh, error cases)
- [x] Create `PolicySchedulerServiceTest.java` (Mockito unit tests for cron jobs)
- [x] Create `GlobalExceptionHandler.java` (REST exception handler)
- [x] Create `GlobalExceptionHandlerTest.java` (403, 400, 500 scenarios)
- [x] Update `docker-compose.yml` with 5 services (Backend, AI, Frontend, DB, Redis)
- [x] Create `frontend/index.html` and `frontend/nginx.conf`
- [ ] Run `mvn clean test` (Maven not installed on this machine — run locally)
- [ ] Run `docker-compose up --build` (Docker not installed on this machine — run locally)
- [ ] Commit with message: "Day 11 - Reached 80% coverage with Testcontainers and Repository tests"

## Day 12 — Docker Finalization & Swagger Docs
- [x] Create `.env` file with all environment variables
- [x] Update `docker-compose.yml` with `env_file`, healthchecks, `depends_on` conditions, shared network
- [x] Create `frontend/Dockerfile` (React multi-stage build)
- [x] Create `frontend/package.json`
- [x] Create `frontend/src/App.js` and `frontend/src/index.js`
- [x] Add `@Operation` and `@ApiResponse` annotations to `PolicyController` (all 6 methods)
- [x] Add `@Operation` and `@ApiResponse` annotations to `AuthController` (all 3 methods)
- [x] Add `@Schema` annotations with examples to `Policy` entity
- [x] Add `@Schema` DTOs (`RegisterRequest`, `LoginRequest`, `RefreshRequest`, `TokenResponse`, `AuthResponse`, `ErrorResponse`) to `AuthController`
- [x] Add `@Schema` DTOs (`PolicyPageResponse`, `PolicyStatsResponse`) to `PolicyController`
- [x] Update `OpenApiConfig.java` with JWT bearer token security scheme
- [ ] Verify Swagger UI at `http://localhost:8080/swagger-ui/index.html` (run locally)
- [ ] Verify Docker Compose starts all 5 services with `docker-compose up --build` (run locally)
- [ ] Commit with message: "Day 12 - Finalized docker-compose with healthchecks and added Swagger documentation"

## Day 14 — Data Seeding & Performance Tuning
- [x] Create `DataSeeder.java` with `@EventListener(ApplicationReadyEvent.class)` — seeds 32 realistic policies (Active, Pending, COMPLETED, DELETED) with idempotency check
- [x] Update `V5__performance_indexes.sql` — added `idx_policies_status_expiry` composite index and `idx_policies_active_not_deleted` partial index
- [x] Update `PolicyRepository.java` — added `findAllActivePoliciesWithDetails()` demonstrating `JOIN FETCH` N+1 prevention pattern
- [ ] Verify 30+ records appear after `docker compose down -v && docker compose up --build` (run locally)
- [ ] Verify search queries use indexes via `EXPLAIN ANALYZE` (run locally)
- [ ] Commit with message: "Day 14 - Implemented 30-record DataSeeder and optimized JPA query performance"

