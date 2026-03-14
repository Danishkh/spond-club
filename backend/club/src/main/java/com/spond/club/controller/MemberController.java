package com.spond.club.controller;

import com.spond.club.dto.RegistrationRequest;
import com.spond.club.dto.RegistrationResponse;
import com.spond.club.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/forms")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/{formId}/registrations")
    public ResponseEntity<RegistrationResponse> register(
            @PathVariable String formId,
            @Valid @RequestBody RegistrationRequest request) {
        return ResponseEntity.ok(memberService.register(formId, request));
    }
}
