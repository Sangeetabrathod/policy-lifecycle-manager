package com.internship.tool.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "policies")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "Policy", description = "Insurance policy entity representing a lifecycle-managed policy record")
public class Policy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier for the policy", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Column(name = "policy_name", nullable = false, length = 255)
    @Schema(description = "Name of the insurance policy", example = "AutoShield Premium", requiredMode = Schema.RequiredMode.REQUIRED)
    private String policyName;

    @Column(name = "policy_type", nullable = false, length = 100)
    @Schema(description = "Type of insurance policy", example = "Auto Insurance", requiredMode = Schema.RequiredMode.REQUIRED)
    private String policyType;

    @Column(nullable = false, length = 50)
    @Schema(description = "Current status of the policy", example = "Active", allowableValues = {"Active", "Pending", "COMPLETED", "DELETED"}, requiredMode = Schema.RequiredMode.REQUIRED)
    private String status;

    @Column(name = "policy_holder", nullable = false, length = 255)
    @Schema(description = "Name of the policy holder", example = "John Doe", requiredMode = Schema.RequiredMode.REQUIRED)
    private String policyHolder;

    @Column(name = "expiry_date")
    @Schema(description = "Policy expiration date (ISO-8601)", example = "2026-12-31")
    private LocalDate expiryDate;

    @Column(name = "is_deleted", nullable = false)
    @Schema(description = "Soft-delete flag", example = "false", accessMode = Schema.AccessMode.READ_ONLY)
    private Boolean isDeleted = false;

    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    @Schema(description = "Timestamp when the policy was created", example = "2024-01-15T10:30:00", accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    @Schema(description = "Timestamp when the policy was last updated", example = "2024-06-20T14:45:00", accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime updatedAt;
}

