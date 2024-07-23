package com.matf.linkvault.utils;

import com.matf.linkvault.exceptions.UserNotFoundException;
import com.matf.linkvault.models.entities.User;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@UtilityClass
public class UserUtil {

    public static Optional<User> getLoggedInUser() throws UserNotFoundException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || authentication.getPrincipal().equals("anonymousUser")) {
            throw new UserNotFoundException();
        }
        return Optional.of ((User) authentication.getPrincipal());
    }

    public boolean hasRole(String roleName) throws UserNotFoundException {
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated() || authentication.getPrincipal().equals("anonymousUser")) {
            throw new UserNotFoundException();
        }

        // Check if the role with roleName exists in the authorities
        for (GrantedAuthority authority : authentication.getAuthorities()) {
            if (authority.getAuthority().equals(roleName)) {
                return true;
            }
        }

        return false;  // The role was not found in the authorities
    }
}
