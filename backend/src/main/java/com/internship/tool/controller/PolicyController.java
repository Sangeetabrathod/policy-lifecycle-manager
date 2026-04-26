package com.internship.tool.controller;

import com.internship.tool.entity.Policy;
import com.internship.tool.repository.PolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/policies")
public class PolicyController {

    @Autowired
    private PolicyRepository policyRepository;

    /**
     * Returns a paginated list of all non-deleted policies.
     * Accessible by all authenticated roles.
     */
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'VIEWER')")
    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> getAllPolicies(Pageable pageable) {
        Page<Policy> page = policyRepository.findByIsDeletedFalse(pageable);
        Map<String, Object> response = new HashMap<>();
        response.put("content", page.getContent());
        response.put("totalElements", page.getTotalElements());
        response.put("totalPages", page.getTotalPages());
        response.put("currentPage", page.getNumber());
        response.put("pageSize", page.getSize());
        return ResponseEntity.ok(response);
    }

    /**
     * Creates a new policy.
     * Restricted to ADMIN and MANAGER roles.
     */
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    @PostMapping("/create")
    public ResponseEntity<Policy> createPolicy(@RequestBody Policy policy) {
        policy.setIsDeleted(false);
        Policy saved = policyRepository.save(policy);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    /**
     * Updates an existing policy by ID.
     * Restricted to ADMIN and MANAGER roles.
     */
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    @PutMapping("/{id}")
    public ResponseEntity<Policy> updatePolicy(@PathVariable Long id, @RequestBody Policy policyDetails) {
        return policyRepository.findByIdAndIsDeletedFalse(id)
                .map(policy -> {
                    policy.setPolicyName(policyDetails.getPolicyName());
                    policy.setPolicyType(policyDetails.getPolicyType());
                    policy.setStatus(policyDetails.getStatus());
                    policy.setPolicyHolder(policyDetails.getPolicyHolder());
                    policy.setExpiryDate(policyDetails.getExpiryDate());
                    Policy updatedPolicy = policyRepository.save(policy);
                    return ResponseEntity.ok(updatedPolicy);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Performs a soft delete by setting the is_deleted flag to TRUE.
     * Restricted to ADMIN role only.
     */
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> softDeletePolicy(@PathVariable Long id) {
        return policyRepository.findByIdAndIsDeletedFalse(id)
                .map(policy -> {
                    policy.setIsDeleted(true);
                    policy.setStatus("DELETED");
                    policyRepository.save(policy);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Searches policies by name or policy holder using a query parameter.
     * Accessible by all authenticated roles.
     */
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'VIEWER')")
    @GetMapping("/search")
    public ResponseEntity<List<Policy>> searchPolicies(@RequestParam(name = "q") String q) {
        List<Policy> results = policyRepository.searchByNameOrHolder(q);
        return ResponseEntity.ok(results);
    }

    /**
     * Returns basic KPI stats for policies.
     * Accessible by all authenticated roles.
     */
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'VIEWER')")
    @GetMapping("/stats")
    public ResponseEntity<Map<String, Object>> getPolicyStats() {
        long totalPolicies = policyRepository.countByIsDeletedFalse();
        long totalActive = policyRepository.countByStatusAndIsDeletedFalse("Active");

        Map<String, Object> stats = new HashMap<>();
        stats.put("totalPolicies", totalPolicies);
        stats.put("totalActivePolicies", totalActive);

        return ResponseEntity.ok(stats);
    }
}
