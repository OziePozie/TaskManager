package com.example.security;

import com.example.models.Person;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

@Component
@NoArgsConstructor
public class UserDetailsImpl implements UserDetails {

    private Person user;

    public UserDetailsImpl(Person user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Возвращает список разрешений (ролей) пользователя
        return user.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.toString()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        // Возвращает пароль пользователя
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        // Возвращает имя пользователя
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        // Проверяет, истек ли срок действия учетной записи пользователя
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // Проверяет, заблокирована ли учетная запись пользователя
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // Проверяет, истек ли срок действия учетных данных пользователя (например, пароля)
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Long getId() {
        // Возвращает идентификатор пользователя
        return user.getId();
    }
}
