package com.spond.club.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spond.club.dto.RegistrationResponse;
import com.spond.club.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Map;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MemberController.class)
class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private MemberService memberService;

    @Test
    void register_returns200_whenValid() throws Exception {
        RegistrationResponse response = new RegistrationResponse(UUID.randomUUID(), "Registration successful!");
        when(memberService.register(any(), any())).thenReturn(response);

        Map<String, String> request = Map.of(
                "memberTypeId", "MT1",
                "firstName", "Test",
                "lastName", "Test2",
                "email", "test@mail.com",
                "phone", "+4712345678",
                "birthDate", "1995-01-01"
        );

        mockMvc.perform(post("/api/forms/group1/registrations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Registration successful!"));
    }

    @Test
    void register_returns400_whenEmailInvalid() throws Exception {
        Map<String, String> request = Map.of(
                "memberTypeId", "MT1",
                "firstName", "Test",
                "lastName", "Test2",
                "email", "invalidMail",
                "phone", "+4712345678",
                "birthDate", "1995-01-01"
        );

        mockMvc.perform(post("/api/forms/group1/registrations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.email").value("Email is invalid"));
    }
}