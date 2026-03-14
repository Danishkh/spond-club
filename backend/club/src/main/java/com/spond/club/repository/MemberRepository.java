package com.spond.club.repository;

import com.spond.club.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface MemberRepository extends JpaRepository<Member, UUID> {
}