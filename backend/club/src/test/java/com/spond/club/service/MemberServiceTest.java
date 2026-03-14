package com.spond.club.service;

import com.spond.club.dto.RegistrationRequest;
import com.spond.club.dto.RegistrationResponse;
import com.spond.club.model.Group;
import com.spond.club.model.MemberType;
import com.spond.club.model.Member;
import com.spond.club.repository.GroupRepository;
import com.spond.club.repository.MemberTypeRepository;
import com.spond.club.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @Mock
    private GroupRepository groupRepository;

    @Mock
    private MemberTypeRepository memberTypeRepository;

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private MemberService memberService;

    @Test
    void register_returnsResponse_whenValid() {
        Group group = new Group();
        group.setId("group1");

        MemberType memberType = new MemberType();
        memberType.setId("Testtype1");

        Member savedMember = new Member();
        savedMember.setId(UUID.randomUUID());

        when(groupRepository.findById("group1")).thenReturn(Optional.of(group));
        when(memberTypeRepository.findById("Testtype1")).thenReturn(Optional.of(memberType));
        when(memberRepository.save(any(Member.class))).thenReturn(savedMember);

        RegistrationRequest request = new RegistrationRequest();
        request.setMemberTypeId("Testtype1");
        request.setFirstName("Test");
        request.setLastName("test2");
        request.setEmail("test@mail.com");
        request.setPhone("+4712345678");
        request.setBirthDate(LocalDate.of(2000, 1, 1));

        RegistrationResponse response = memberService.register("group1", request);

        assertNotNull(response.getId());
        assertEquals("Registration successful!", response.getMessage());
        verify(memberRepository, times(1)).save(any(Member.class));
    }

    @Test
    void register_throws_whenGroupNotFound() {
        when(groupRepository.findById("INVALID")).thenReturn(Optional.empty());

        RegistrationRequest request = new RegistrationRequest();
        request.setMemberTypeId("Testtype1");

        assertThrows(NoSuchElementException.class, () -> memberService.register("INVALID", request));
    }
}