package com.communitter.api.user;

import com.communitter.api.community.Community;
import com.communitter.api.community.Subscription;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="users")
public class User implements UserDetails {
    @Id
    @SequenceGenerator(
            name="user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
    generator = "user_sequence")
    private Long id;
    @Column(nullable = false,unique = true)
    private String username;
    @Column(nullable = false,unique = true)
    private String email;
    @JsonIgnore
    @Column(nullable = false)
    private String password;
    @Column(nullable = true)
    private String about;
    @Column(nullable = true)
    private String header;
    @Column(nullable = true)
    private String avatar;

    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
    @Cascade(org.hibernate.annotations.CascadeType.REMOVE)
    @JsonManagedReference("user-subs")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Subscription> subscriptions;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("Visitor"));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
