package com.spond.club.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.OffsetDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class GroupDto {
    private String formId;
    private String clubId;
    private String title;
    private String description;
    private OffsetDateTime registrationOpens;
    private List<MemberTypeDto> memberTypes;
}