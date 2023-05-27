package com.nulp.railway.service;

import com.nulp.railway.entity.Role;
import com.nulp.railway.entity.enums.ERole;
import com.nulp.railway.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public Role getByName(ERole eRole) {
        return roleRepository.findByName(eRole).orElseGet(() -> {
            Role role = new Role();
            role.setName(eRole);
            return roleRepository.save(role);
        });
    }
}
