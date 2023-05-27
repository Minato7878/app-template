package com.nulp.railway.service.auth;

import com.nulp.railway.entity.enums.ERole;
import com.nulp.railway.security.UserDetailsImpl;
import com.nulp.railway.repository.UserRepository;
import com.nulp.railway.utils.CommonMessageBundle;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service class which contains methods responsible for
 * getting user by username and creating Spring UserDetails object
 * based on user parameters.
 *
 * @author danylo.matviykiv@gmail.com
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final CommonMessageBundle messageBundle;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) {
        var user = userRepository.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException(String.format(messageBundle.getMessage("user.email.not.found.exception"), email)));
        return UserDetailsImpl.create(user, ERole.USER);
    }
}