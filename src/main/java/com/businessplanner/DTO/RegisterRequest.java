package com.businessplanner.DTO;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RegisterRequest {
    private String name;
    private String email;
    private String password;
}