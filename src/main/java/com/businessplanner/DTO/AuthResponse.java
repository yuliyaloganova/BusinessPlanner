package com.businessplanner.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse{
    private String token;
    private Long userId;
    private String email;
    private String username;
    private String role;
}