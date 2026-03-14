package com.spond.club.repository;

import com.spond.club.model.MemberType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberTypeRepository extends JpaRepository<MemberType, String> {
}