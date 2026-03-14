package com.spond.club.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class RegistrationResponse {
    private UUID id;
    private String message;
}