package com.spond.club.service;

import com.spond.club.dto.GroupDto;
import com.spond.club.dto.MemberTypeDto;
import com.spond.club.model.Group;
import com.spond.club.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;

    public GroupDto getGroup(String formId) {
        Group group = groupRepository.findById(formId)
                .orElseThrow(() -> new NoSuchElementException("Group not found: " + formId));

        List<MemberTypeDto> memberTypes = group.getMemberTypes().stream()
                .map(mt -> new MemberTypeDto(mt.getId(), mt.getName()))
                .toList();

        return new GroupDto(
                group.getId(),
                group.getClubId(),
                group.getTitle(),
                group.getDescription(),
                group.getRegistrationOpens(),
                memberTypes
        );
    }
}