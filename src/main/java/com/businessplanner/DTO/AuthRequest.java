package com.businessplanner.DTO;

public record AuthRequest(
        String email,
        String password
) {}
