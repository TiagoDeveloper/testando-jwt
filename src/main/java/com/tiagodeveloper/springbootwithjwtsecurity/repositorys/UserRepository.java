package com.tiagodeveloper.springbootwithjwtsecurity.repositorys;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserRepository {

    private final static Map<String, UserDetails> userDetailsMap = new HashMap<>();

    static {
        userDetailsMap.put("tiagopereirafonseca@gmail.com", new UserDetailsImpl("tiagopereirafonseca@gmail.com", "senha", List.of(new SimpleGrantedAuthority("USER")), true));
        userDetailsMap.put("lucaspereira@gmail.com", new UserDetailsImpl("lucaspereira@gmail.com", "irmao", List.of(new SimpleGrantedAuthority("USER")), true));
        userDetailsMap.put("taniamaria@gmail.com", new UserDetailsImpl("taniamaria@gmail.com", "mae", List.of(new SimpleGrantedAuthority("ADMIN")), true));
        userDetailsMap.put("bloqueado@gmail.com", new UserDetailsImpl("bloqueado@gmail.com", "senha", List.of(new SimpleGrantedAuthority("ADMIN")), true));
    }

    private static class UserDetailsImpl implements UserDetails {

        private final String password;
        private final String userName;
        private final boolean accountNonExpired;
        private final boolean accountNonLocked;
        private final boolean credentialsNonExpired;
        private final boolean enabled;
        private Collection<GrantedAuthority> authorities = Collections.emptyList();


        public UserDetailsImpl(String userName, String password, Collection<GrantedAuthority> authorities, boolean enabled) {
            this.userName = userName;
            this.password = new BCryptPasswordEncoder(10).encode(password);
            this.authorities = authorities;
            this.enabled = enabled;
            this.accountNonExpired = true;
            this.accountNonLocked = true;
            this.credentialsNonExpired = true;
        }

        public UserDetailsImpl(String userName, String password, boolean accountNonExpired, boolean accountNonLocked, boolean credentialsNonExpired, boolean enabled, Collection<GrantedAuthority> authorities) {
            this.userName = userName;
            this.password = new BCryptPasswordEncoder(10).encode(password);
            this.accountNonExpired = accountNonExpired;
            this.accountNonLocked = accountNonLocked;
            this.credentialsNonExpired = credentialsNonExpired;
            this.enabled = enabled;
            this.authorities = authorities;
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return this.authorities;
        }

        @Override
        public String getPassword() {
            return this.password;
        }

        @Override
        public String getUsername() {
            return this.userName;
        }

        @Override
        public boolean isAccountNonExpired() {
            return this.accountNonExpired;
        }

        @Override
        public boolean isAccountNonLocked() {
            return this.accountNonLocked;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return this.credentialsNonExpired;
        }

        @Override
        public boolean isEnabled() {
            return this.enabled;
        }
    }

    public UserDetails findByUserName(String userName) {
        return User.withUserDetails(userDetailsMap.get(userName)).build();
    }

}
