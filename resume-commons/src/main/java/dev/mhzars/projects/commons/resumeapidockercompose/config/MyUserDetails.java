package dev.mhzars.projects.commons.resumeapidockercompose.config;


import dev.mhzars.projects.commons.resumeapidockercompose.model.CommonAuthUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collection;
import java.util.List;

public class MyUserDetails implements UserDetails {
    private final String username;
    private final String password;
    private final boolean active;
    private final List<SimpleGrantedAuthority> authorityList;

    public MyUserDetails(CommonAuthUser authUserModel) {
        this.username = authUserModel.getUsername();
        this.password = new BCryptPasswordEncoder().encode(authUserModel.getPassword());
        this.active = authUserModel.isActive();
        this.authorityList = getRoles(authUserModel);
    }

    private List<SimpleGrantedAuthority> getRoles(CommonAuthUser authUserModel) {
        return authUserModel.getAuthRoles()
                .stream()
                .map(authRole -> new SimpleGrantedAuthority(authRole.getRole()))
                .toList();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorityList;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
        return active;
    }

}
