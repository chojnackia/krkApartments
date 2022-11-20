package com.example.krkapartments.persistence.admin.entity;

import com.example.krkapartments.persistence.shared.UserRole;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

@Entity
@Table(name = "ADMINS")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminEntity implements UserDetails {


    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "UUID")
    private UUID id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @NotBlank(message = "Email is mandatory")
    @Column(unique = true)
    private String email;

    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private boolean active;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(getRole().name()));
    }

    @Override
    public String getUsername() {
        return email;
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
        return isActive();
    }
}
