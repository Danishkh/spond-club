package com.spond.club.controller;

import com.spond.club.dto.GroupDto;
import com.spond.club.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/forms")
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;

    @GetMapping("/{formId}")
    public ResponseEntity<GroupDto> getForm(@PathVariable String formId) {
        return ResponseEntity.ok(groupService.getGroup(formId));
    }
}

