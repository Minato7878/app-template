package com.nulp.railway.service;

import com.nulp.railway.entity.User;
import com.nulp.railway.exception.CustomAuthException;
import com.nulp.railway.exception.EntityNotFoundException;
import com.nulp.railway.repository.UserRepository;
import com.nulp.railway.security.UserDetailsImpl;
import com.nulp.railway.utils.CommonMessageBundle;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final static String ANON_USER = "anonymousUser";
    private final CommonMessageBundle messageBundle;
    private final UserRepository userRepository;

    /**
     * This method returned user that currently is logged in system.
     * User is stored in Spring Security Context.
     *
     * @return logged User
     */
    public User getCurrentUser() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getPrincipal().equals(ANON_USER)) {
            return new User();
        }
        var userDetails = (UserDetailsImpl) auth.getPrincipal();
        if (Objects.nonNull(userDetails))
            return userRepository.findById(userDetails.getId())
                    .orElseThrow(() -> new EntityNotFoundException(
                            String.format(messageBundle.getMessage("user.not.found.exception"), userDetails.getId()))
                    );
        throw new CustomAuthException(messageBundle.getMessage("user.not.logged.exception"));
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format(messageBundle.getMessage("user.not.found.exception"), id)));
    }

    public User getByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format(messageBundle.getMessage("user.email.not.found.exception"), email)));
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public User update(User user, Long userId) {
        user.setId(userId);
        return userRepository.save(user);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public Boolean emailExists(String email) {
        return userRepository.existsByEmail(email);
    }
}