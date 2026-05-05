package com.internship.tool.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO for policy statistics.
 * Returns clean JSON: {"totalPolicies": 30, "activePolicies": 21}
 */
@Schema(description = "Policy statistics summary")
public record PolicyStatsDTO(
        @JsonProperty("totalPolicies")
        @Schema(description = "Total number of non-deleted policies", example = "30")
        Long totalPolicies,
        
        @JsonProperty("activePolicies")
        @Schema(description = "Number of active policies (LOWER(status) = 'active' AND isDeleted=false)", example = "21")
        Long activePolicies
) {
}

