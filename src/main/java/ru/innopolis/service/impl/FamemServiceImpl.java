package ru.innopolis.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.innopolis.domain.Famem;
import ru.innopolis.domain.User;
import ru.innopolis.repository.FamemRepository;
import ru.innopolis.service.FamemService;
import ru.innopolis.service.UserService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
@Transactional
@Slf4j
public class FamemServiceImpl implements FamemService {
    @PersistenceContext
    private EntityManager em;

    @Autowired
    private FamemRepository famemRepository;

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void save(Famem famem) {
        log.info("saved famem to DB {}", famem);
        famemRepository.save(famem);
    }

    @Override
    public Famem findById(Long id) {
        log.info("find famem by ID {}", id);
        return famemRepository.findById(id).get();
    }

    @Override
    public void delete(Famem famem) {
        log.info("delete famem from DB {}", famem);
        famemRepository.delete(famem);
    }

    @Override
    public List<Famem> findAll() {
        log.info("find all famem");
        return famemRepository.findAll();
    }

    @Override
    public Famem findByUserid(Long id) {
        return famemRepository.findFirstByUserid(id);
    }

    /**
     * Find famem with appropriate userId and returns it.
     * In case famem is absent, create new famem instance with
     * appropriate userId.
     *
     * @param id - userId, which is associated with the user and famem.
     * @return - Famem object.
     */
    @Override
    public Famem getByUserid(Long id) {
        Famem famem = new Famem();
        if (findByUserid(id) == null) {
            famem.setUserid(id);
            save(famem);
            log.info("saved new family member associated with the user {}", id);
        } else {
            famem = findByUserid(id);
            log.debug("get family member{}", famem);
        }
        return famem;
    }

    @Override
    public void update(Famem newFamemInfo) {
        Famem famem = getByUserid(newFamemInfo.getUserid());
        famem.setDatebirth(newFamemInfo.getDatebirth());
        famem.setName(newFamemInfo.getName());
        famem.setPatronymic(newFamemInfo.getPatronymic());
        famem.setSurname(newFamemInfo.getSurname());
        save(famem);
    }

    @Override
    public Famem getByLogin(String login) {
        User user = userService.findFirstByLogin(login);
        Long userId = user.getUserid();
        return getByUserid(userId);
    }

    @Override
    public void setFamilyid(String login, Long familyid) {
        Famem famem = getByLogin(login);
        famem.setFamilyid(familyid);
    }
}