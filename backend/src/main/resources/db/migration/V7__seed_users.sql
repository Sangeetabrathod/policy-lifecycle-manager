-- Flyway migration: V7__seed_users.sql
-- Seeds default admin user for authentication testing

INSERT INTO users (username, password, email, role) VALUES 
('admin', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', 'admin@policy.local', 'ADMIN')
ON CONFLICT (username) DO NOTHING;

-- Password: admin123 (BCrypt encoded)
-- Role: ADMIN for full access during testing
-- Use: .\test-login.ps1 with username: "admin", password: "admin123"

