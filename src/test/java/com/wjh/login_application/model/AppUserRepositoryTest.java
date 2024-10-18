package com.wjh.login_application.model;

import com.wjh.login_application.repository.AppUserRepository;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class AppUserRepositoryTest {

    @Autowired
    private AppUserRepository appUserRepository;

    @ParameterizedTest
    @CsvSource(delimiterString = " | ", value = {"user | true", "test | false"})
    void checkFindByUsername(String username, boolean result) {
        // WHEN
        Optional<AppUser> optionalAppUser = appUserRepository.findByUsername(username);

        // THEN
        assertEquals(result, optionalAppUser.isPresent());
    }
}