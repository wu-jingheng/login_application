package com.wjh.login_application.service;

import com.wjh.login_application.exception.DuplicateAppUserException;
import com.wjh.login_application.model.AppUser;
import com.wjh.login_application.model.AppUserDto;
import com.wjh.login_application.repository.AppUserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AppUserDetailsServiceTest {

    @Mock
    private AppUserRepository appUserRepository;

    @InjectMocks
    private AppUserDetailsService appUserDetailsService;

    private static final AppUser appUser = new AppUser();

    @BeforeAll
    static void setUp() {
        appUser.setName("test user");
        appUser.setUsername("test_username");
        appUser.setPassword("test_password");
        appUser.setRole("USER");
    }

    @Test
    void loadUserByUsername_UserExists_ReturnsUserDetails() {
        // GIVEN
        when(appUserRepository.findByUsername("test_username")).thenReturn(Optional.of(appUser));

        // WHEN
        var userDetails = appUserDetailsService.loadUserByUsername("test_username");

        // THEN
        assertNotNull(userDetails);
        assertEquals("test_username", userDetails.getUsername());
        assertEquals("test_password", userDetails.getPassword());
        assertTrue(userDetails.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_USER")));
    }

    @Test
    void loadUserByUsername_UserDoesNotExist_ThrowsUsernameNotFoundException() {
        // GIVEN
        when(appUserRepository.findByUsername("unknown")).thenReturn(Optional.empty());

        // WHEN & THEN
        assertThrows(UsernameNotFoundException.class, () -> appUserDetailsService.loadUserByUsername("unknown"));
    }

    @Test
    void saveUser_SuccessfulSave() {
        // GIVEN
        when(appUserRepository.saveAndFlush(any(AppUser.class))).thenReturn(appUser);

        // WHEN
        appUserDetailsService.saveUser(appUser);

        // THEN
        verify(appUserRepository, times(1)).saveAndFlush(appUser);
    }

    @Test
    void saveUser_DuplicateUser_ThrowsDuplicateAppUserException() {
        // GIVEN
        when(appUserRepository.saveAndFlush(any(AppUser.class)))
                .thenThrow(new DataIntegrityViolationException("Duplicate entry"));

        // WHEN & THEN
        assertThrows(DuplicateAppUserException.class, () -> appUserDetailsService.saveUser(appUser));
        verify(appUserRepository, times(1)).saveAndFlush(appUser);
    }

    @Test
    void getAppUserDtoByUsername_UserExists_ReturnsAppUserDto() {
        // GIVEN
        when(appUserRepository.findByUsername("test_username")).thenReturn(Optional.of(appUser));

        // WHEN
        AppUserDto appUserDto = appUserDetailsService.getAppUserDtoByUsername("test_username");

        // THEN
        assertEquals("test_username", appUserDto.username());
        assertEquals("User", appUserDto.role());
    }

    @Test
    void getAppUserDtoByUsername_UserDoesNotExist_ThrowsUsernameNotFoundException() {
        // GIVEN
        when(appUserRepository.findByUsername("unknown")).thenReturn(Optional.empty());

        // WHEN & THEN
        assertThrows(UsernameNotFoundException.class, () -> appUserDetailsService.getAppUserDtoByUsername("unknown"));
    }
}
