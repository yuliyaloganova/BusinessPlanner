package com.businessplanner.DTO;

public record RegisterRequest(
        String name,
        String email,
        String password
) {}