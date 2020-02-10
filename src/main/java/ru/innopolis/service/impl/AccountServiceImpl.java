package ru.innopolis.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.innopolis.domain.Account;
import ru.innopolis.repository.AccountRepository;
import ru.innopolis.service.AccountService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@Slf4j
public class AccountServiceImpl implements AccountService {
    @PersistenceContext
    private EntityManager em;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public List<Account> findByFamemid(Long famemid) {
        return accountRepository.findAllByFamemid(famemid);
    }


    @Override
    public void save(Account account) {
        log.info("saved account to DB {}", account);
        accountRepository.save(account);
    }

    @Override
    public Account findById(Long id) {
        log.info("find account by ID {}", id);
        return accountRepository.findById(id).get();
    }

    @Override
    public void delete(Account account) {
        log.info("delete account from DB {}", account);
        accountRepository.delete(account);
    }

    @Override
    public List<Account> findAll() {
        log.info("find all account");
        return accountRepository.findAll();
    }

    @Override
    public Map<Long, Double> balance(Long userid) {
        Query query = em.createNativeQuery("  select a.num, a.amount\n" +
                "  from account a\n" +
                "  join famem f on f.famemid = a.famemid\n" +
                "  where f.userid = ?");
        query.setParameter(1, userid);
        List<Object[]> objects = query.getResultList();
        Map<Long, Double> balances = new HashMap<>();
        for (Object[] obj : objects) {
            balances.put(((BigDecimal) obj[0]).longValue(), ((BigDecimal) obj[1]).doubleValue());
        }
        return balances;
    }
}