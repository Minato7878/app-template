package com.nulp.railway.service.auth;

import com.nulp.railway.dto.auth.SignupRequest;
import com.nulp.railway.dto.auth.UpdatePasswordRequest;
import com.nulp.railway.dto.JwtResponse;
import com.nulp.railway.entity.Role;
import com.nulp.railway.entity.User;
import com.nulp.railway.entity.enums.ERole;
import com.nulp.railway.mapper.UserMapper;
import com.nulp.railway.repository.RoleRepository;
import com.nulp.railway.security.JwtTokenProvider;
import com.nulp.railway.security.UserDetailsImpl;
import com.nulp.railway.service.UserService;
import com.nulp.railway.utils.CommonMessageBundle;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.util.Collections;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserMapper userMapper;
    private final UserService userService;
    private final JwtTokenProvider tokenProvider;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final CommonMessageBundle messageBundle;
    private final AuthenticationManager authenticationManager;

    /**
     * This method allows user to register in system
     *
     * @param signupRequest registration request with user information
     * @return JwtResponse that contains token
     * @throws AuthenticationException if login or email is not unique
     */
    public JwtResponse register(SignupRequest signupRequest) throws AuthenticationException {
        log.info("Register new User {}", signupRequest.getEmail());
        String email = signupRequest.getEmail();
        String password = signupRequest.getPassword();

        if (userService.emailExists(signupRequest.getEmail())) {
            throw new AuthenticationException(messageBundle.getMessage("user.email.not.unique.exception"));
        }

        var user = new User();
        user.setEmail(signupRequest.getEmail());
        user.setFirstName(signupRequest.getFirstName());
        user.setLastName(signupRequest.getLastName());
        user.setNickname(signupRequest.getNickname());
        user.setPassword(passwordEncoder.encode(password));

        var role = roleRepository.findByName(ERole.GUEST)
                .orElseGet(() -> roleRepository.save(new Role(ERole.GUEST)));

        user.setRoles(Collections.singleton(role));
        userService.save(user);

        return login(email, password);
    }

    /**
     * This method authorize user in system.
     *
     * @param email    - email from login request
     * @param password - encrypted password
     * @return JwtResponse that contains token
     */
    public JwtResponse login(String email, String password) {
        log.info("Logging user {}", email);
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(
                        email, password
                );

        var authentication = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        var userDetails = (UserDetailsImpl) authentication.getPrincipal();
        var user = userService.getById(userDetails.getId());

        return createJwtResponse(user);
    }

    public JwtResponse updatePassword(UpdatePasswordRequest request) {
        var user = userService.getCurrentUser();
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userService.update(user, user.getId());
        return createJwtResponse(user);
    }

    private JwtResponse createJwtResponse(User user) {
        var jwt = tokenProvider.generateTokenFromEmail(user.getEmail());

        return JwtResponse.builder()
                .token(jwt)
                .userDto(userMapper.toDto(user))
                .build();
    }
}