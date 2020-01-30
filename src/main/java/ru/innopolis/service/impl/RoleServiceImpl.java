package ru.innopolis.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.innopolis.domain.Role;
import ru.innopolis.repository.RoleRepository;
import ru.innopolis.service.RoleService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
@Transactional
@Slf4j
public class RoleServiceImpl implements RoleService {
    @PersistenceContext
    private EntityManager em;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void save(Role role) {
        log.info("saved role to DB {}", role);
        roleRepository.save(role);
    }

    @Override
    public Role findById(Long id) {
        log.info("find role by ID {}", id);
        return roleRepository.findById(id).get();
    }

    @Override
    public void delete(Role role) {
        log.info("delete account from DB {}", role);
        roleRepository.delete(role);
    }

    @Override
    public List<Role> findAll() {
        log.info("find all role");
        return roleRepository.findAll();
    }
}