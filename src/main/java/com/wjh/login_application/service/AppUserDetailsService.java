package com.wjh.login_application.service;

import com.wjh.login_application.exception.DuplicateAppUserException;
import com.wjh.login_application.model.AppUser;
import com.wjh.login_application.model.AppUserDto;
import com.wjh.login_application.repository.AppUserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.lang.NonNull;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Log4j2
@Service
public class AppUserDetailsService implements UserDetailsService {
    private final AppUserRepository appUserRepository;

    public AppUserDetailsService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser userFound = getAppUserByUsername(username);
        return User.builder()
                .username(userFound.getUsername())
                .password(userFound.getPassword())
                .roles(userFound.getRole())
                .build();
    }

    public void saveUser(@NonNull AppUser newAppUser) {
        try {
            appUserRepository.saveAndFlush(newAppUser);
        } catch (DataIntegrityViolationException ex) {
            log.info("Failed to save {}", newAppUser.toString());
            throw new DuplicateAppUserException(newAppUser.toString());
        }
        log.info("Successfully saved {}", newAppUser.toString());
    }

    public AppUserDto getAppUserDtoByUsername(String username) {
        AppUser appUser = getAppUserByUsername(username);
        return new AppUserDto(appUser.getName(), appUser.getUsername(), appUser.getRole());
    }

    public AppUser getAppUserByUsername(String username) {
        Optional<AppUser> appUser = appUserRepository.findByUsername(username);
        if (appUser.isEmpty()) {
            throw new UsernameNotFoundException(username);
        }
        return appUser.get();
    }
}
