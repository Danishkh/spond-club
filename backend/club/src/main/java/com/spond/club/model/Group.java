package com.spond.club.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Table(name = "groups")
@Getter @Setter
public class Group {

    @Id
    private String id;

    @Column(name = "club_id", nullable = false)
    private String clubId;

    @Column(nullable = false)
    private String title;

    private String description;

    @Column(name = "registration_opens", nullable = false)
    private OffsetDateTime registrationOpens;

    @OneToMany(mappedBy = "group", fetch = FetchType.EAGER)
    private List<MemberType> memberTypes;
}
