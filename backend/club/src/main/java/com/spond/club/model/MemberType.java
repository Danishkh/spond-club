package com.spond.club.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "member_types")
@Getter @Setter
public class MemberType {
    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "form_id", nullable = false)
    private Group group;

    @Column(nullable = false)
    private String name;
}