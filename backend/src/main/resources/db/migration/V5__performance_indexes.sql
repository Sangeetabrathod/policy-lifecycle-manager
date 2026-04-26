-- Flyway migration: V5__performance_indexes.sql
-- Adds composite indexes to optimize frequently used search and filter queries

-- Composite index for soft-delete + status filtering (stats, list views)
CREATE INDEX IF NOT EXISTS idx_policies_status_isdeleted ON policies(status, is_deleted);

-- Composite index for policy_name search with is_deleted filter
CREATE INDEX IF NOT EXISTS idx_policies_name_isdeleted ON policies(policy_name, is_deleted);

-- Index for expiry_date queries used by scheduled jobs
CREATE INDEX IF NOT EXISTS idx_policies_expiry ON policies(expiry_date);

-- Composite index for policy_holder search with is_deleted filter
CREATE INDEX IF NOT EXISTS idx_policies_holder_isdeleted ON policies(policy_holder, is_deleted);

