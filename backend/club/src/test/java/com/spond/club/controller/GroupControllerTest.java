package com.spond.club.controller;

import com.spond.club.dto.GroupDto;
import com.spond.club.dto.MemberTypeDto;
import com.spond.club.service.GroupService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.NoSuchElementException;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(GroupController.class)
class GroupControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GroupService groupService;

    @Test
    void getGroup_returns200_whenGroupExists() throws Exception {
        GroupDto groupDto = new GroupDto(
                "group1",
                "britsport",
                "Testform",
                "Test Description",
                OffsetDateTime.now().minusDays(1),
                List.of(new MemberTypeDto("MT1", "Active"))
        );

        when(groupService.getGroup("group1")).thenReturn(groupDto);

        mockMvc.perform(get("/api/forms/group1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.formId").value("group1"))
                .andExpect(jsonPath("$.title").value("Testform"))
                .andExpect(jsonPath("$.memberTypes[0].name").value("Active"));
    }

    @Test
    void getGroup_returns404_whenGroupNotFound() throws Exception {
        when(groupService.getGroup("INVALID")).thenThrow(new NoSuchElementException("INVALID"));

        mockMvc.perform(get("/api/forms/INVALID"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("INVALID"));
    }
}