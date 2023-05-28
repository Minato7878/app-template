package com.nulp.railway.controller;

import com.nulp.railway.dto.auth.LoginRequest;
import com.nulp.railway.dto.auth.SignupRequest;
import com.nulp.railway.dto.JwtResponse;
import com.nulp.railway.service.auth.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Slf4j
@Validated
@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private static final String LOG_MESSAGE = "Endpoint - {}() call";
    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> authenticateUser(@RequestBody @Valid LoginRequest loginRequest) {
        log.debug(LOG_MESSAGE, "authenticateUser");
        var jwt = authenticationService.login(loginRequest.getEmail(), loginRequest.getPassword());
        var headers = new HttpHeaders();
        headers.setBearerAuth(jwt.getToken());
        return ResponseEntity.ok()
                .headers(headers)
                .body(jwt);
    }

    @PostMapping("/register")
    public ResponseEntity<JwtResponse> registerUser(@RequestBody @Valid SignupRequest signupRequest)
            throws AuthenticationException {
        log.debug(LOG_MESSAGE, "registerUser");
        var jwt = authenticationService.register(signupRequest);
        var headers = new HttpHeaders();
        headers.setBearerAuth(jwt.getToken());
        return ResponseEntity.ok()
                .headers(headers)
                .body(jwt);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {
        // Invalidate the session and clear the authentication token
        SecurityContextHolder.clearContext();
        request.getSession().invalidate();

        // Redirect the user to the home page
        return ResponseEntity.ok("You have been logged out successfully");
    }
}