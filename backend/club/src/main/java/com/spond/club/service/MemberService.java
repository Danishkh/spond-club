package com.spond.club.service;

import com.spond.club.dto.RegistrationRequest;
import com.spond.club.dto.RegistrationResponse;
import com.spond.club.model.Group;
import com.spond.club.model.MemberType;
import com.spond.club.model.Member;
import com.spond.club.repository.GroupRepository;
import com.spond.club.repository.MemberRepository;
import com.spond.club.repository.MemberTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final GroupRepository groupRepository;
    private final MemberTypeRepository memberTypeRepository;
    private final MemberRepository memberRepository;

    public RegistrationResponse register(String formId, RegistrationRequest request) {
        Group group = groupRepository.findById(formId)
                .orElseThrow(() -> new NoSuchElementException("Group not found: " + formId));

        MemberType memberType = memberTypeRepository.findById(request.getMemberTypeId())
                .orElseThrow(() -> new NoSuchElementException("Member type not found: " + request.getMemberTypeId()));

        Member member = new Member();
        member.setGroup(group);
        member.setMemberType(memberType);
        member.setFirstName(request.getFirstName());
        member.setLastName(request.getLastName());
        member.setEmail(request.getEmail());
        member.setPhone(request.getPhone());
        member.setBirthDate(request.getBirthDate());
        member.setSubmittedAt(OffsetDateTime.now());

        Member saved = memberRepository.save(member);

        return new RegistrationResponse(saved.getId(), "Registration successful!");
    }
}