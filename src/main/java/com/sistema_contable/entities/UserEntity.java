package com.sistema_contable.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@NamedQuery(name="User.findByEmail", query="select u from UserEntity u where u.email=:email")
@Getter
@Setter
@NoArgsConstructor // Constructor sin argumentos para Lombok
@AllArgsConstructor // Constructor con todos los argumentos
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name="users")
public class UserEntity implements UserDetails { // Implementa UserDetails en lugar de extender User

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="email", nullable=false, unique=true)
    private String email;

    @Column(name="password", nullable=false)
    private String password;

    @Column(name="phone", nullable = true)
    private String phone;

    @Column(name="status", nullable=true)
    private Boolean status;

    @Enumerated(EnumType.STRING)
    @Column(name="role", nullable=false)
    private Role role;

    public enum Role {
        ADMIN,
        USER,
        GUEST
    }

    // Métodos de UserDetails
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email; // Usamos el email como username
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
        return status != null && status;
    }
}