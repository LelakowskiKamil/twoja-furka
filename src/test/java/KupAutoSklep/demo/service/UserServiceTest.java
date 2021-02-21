package KupAutoSklep.demo.service;

import KupAutoSklep.demo.domain.model.login.Role;
import KupAutoSklep.demo.domain.model.login.User;
import KupAutoSklep.demo.domain.repository.UserRepository;
import KupAutoSklep.demo.web.command.CreateUserCommand;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("tests")
public class UserServiceTest {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Mock
    private UserRepository userRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        userService = new UserServiceImpl(userRepository, passwordEncoder);
    }

    @Test
    void saveUserTest() {
        CreateUserCommand createUserCommand = mock(CreateUserCommand.class);
        when(createUserCommand.getEmail()).thenReturn("username@email");
        when(createUserCommand.getUsername()).thenReturn("username");
        when(createUserCommand.getPassword()).thenReturn("!Haslo123");

        User expectedUser = new User();
        expectedUser.setUsername("username");
        expectedUser.setPassword("!Haslo123");
        expectedUser.setEmail("username@email");
        expectedUser.setEnabled(false);
        expectedUser.setRoles(Arrays.asList(new Role("ROLE_USER")));
        expectedUser.setOffers(Arrays.asList());
        User us = new User(createUserCommand.getEmail(), createUserCommand.getUsername(), createUserCommand.getPassword(), Arrays.asList(new Role("ROLE_USER")), createUserCommand.isEnabled(), Arrays.asList());
        doReturn(expectedUser).when(userRepository).save(us);
        User createdUser = userService.save(createUserCommand);

        assertThat(createdUser.getEmail()).isEqualTo("username@email");
        assertThat(createdUser.getUsername()).isEqualTo("username");
    }
}
