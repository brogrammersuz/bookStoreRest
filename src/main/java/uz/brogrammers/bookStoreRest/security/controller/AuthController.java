package uz.brogrammers.bookStoreRest.security.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.brogrammers.bookStoreRest.security.JwtProvider;
import uz.brogrammers.bookStoreRest.security.dto.LoginRequest;
import uz.brogrammers.bookStoreRest.security.dto.LoginResponse;
import uz.brogrammers.bookStoreRest.user.dto.UserRegistrationRequest;
import uz.brogrammers.bookStoreRest.user.entity.User;
import uz.brogrammers.bookStoreRest.user.enums.RoleName;
import uz.brogrammers.bookStoreRest.user.service.RoleService;
import uz.brogrammers.bookStoreRest.user.service.UserService;

import java.time.ZonedDateTime;
import java.util.Set;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    @PostMapping("/login")
    public ResponseEntity authenticateUser(@Valid @RequestBody LoginRequest request) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateJwtToken(authentication);

        return ResponseEntity.ok(new LoginResponse(jwt));

    }

    @PostMapping(value = "/register")
    public ResponseEntity<?> register(@RequestBody UserRegistrationRequest request) {

        if (userService.exitsByUsername(request.getUsername())) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        var role = roleService.findByName(RoleName.ROLE_USER);
        String firstName = StringUtils.capitalize(request.getFirstName().trim().toLowerCase());
        String lastName = StringUtils.capitalize(request.getLastName().trim().toLowerCase());

        User user = User.builder()
                .created(ZonedDateTime.now())
                .firstName(firstName)
                .lastName(lastName)
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(Set.of(role))
                .build();

        var savedUser = userService.save(user);

        return ResponseEntity.ok().body(savedUser);
    }

}
