# Day 10 Implementation TODO

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
- [ ] Run `mvn clean test` (Maven not installed on this machine — run locally)
- [ ] Run `docker-compose up --build` (Docker not installed on this machine — run locally)
- [ ] Commit with message: "Day 10 - Added MockMvc integration tests and finalized soft delete logic"

