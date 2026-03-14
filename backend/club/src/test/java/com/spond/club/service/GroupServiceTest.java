package com.spond.club.service;

import com.spond.club.dto.GroupDto;
import com.spond.club.model.Group;
import com.spond.club.model.MemberType;
import com.spond.club.repository.GroupRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GroupServiceTest {

    @Mock
    private GroupRepository groupRepository;

    @InjectMocks
    private GroupService groupService;

    @Test
    void getForm_returnsGroupDto_whenGroupExists() {
        MemberType memberType = new MemberType();
        memberType.setId("MT1");
        memberType.setName("Active");

        Group group = new Group();
        group.setId("group1");
        group.setClubId("britsport");
        group.setTitle("Testform");
        group.setDescription("Test Description");
        group.setRegistrationOpens(OffsetDateTime.now().minusDays(1));
        group.setMemberTypes(List.of(memberType));

        when(groupRepository.findById("group1")).thenReturn(Optional.of(group));

        GroupDto result = groupService.getGroup("group1");

        assertEquals("group1", result.getFormId());
        assertEquals("britsport", result.getClubId());
        assertEquals("Testform", result.getTitle());
        assertEquals(1, result.getMemberTypes().size());
        assertEquals("Active", result.getMemberTypes().get(0).getName());
    }

    @Test
    void getGroup_throwsException_whenGroupNotFound() {
        when(groupRepository.findById("INVALID")).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> groupService.getGroup("INVALID"));
    }
}