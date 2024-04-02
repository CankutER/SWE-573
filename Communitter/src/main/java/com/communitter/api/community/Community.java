package com.communitter.api.community;

import com.communitter.api.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="communities")
public class Community {
    @Id
    @SequenceGenerator(
            name="community_sequence",
            sequenceName = "community_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "community_sequence")
    private Long id;
    @Column(nullable = false)
    private String name;
    private String about;

    @Column(name = "is_public",nullable = false)
    private boolean isPublic;

    @OneToOne
    @JoinColumn(name = "creator_id")
    private User creator;

    @OneToMany(mappedBy = "community")
    @Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    private Set<Subscription> subscriptions;

}
