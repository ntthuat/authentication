package com.ntthuat.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ntthuat
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse {

    private Boolean success;

    private String message;
}
