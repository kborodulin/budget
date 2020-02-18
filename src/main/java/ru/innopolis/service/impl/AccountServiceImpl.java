package ru.innopolis.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.innopolis.domain.Account;
import ru.innopolis.domain.Famem;
import ru.innopolis.repository.AccountRepository;
import ru.innopolis.service.AccountService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
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

    @Override
    public List<Account> findAllByUser(Long userid) {
        Query query = em.createNativeQuery(
                "select a.accountid, a.name from account a \n" +
                        "join famem f on a.famemid = f.famemid\n" +
                        "where f.userid = ? and a.isclosesign = 0"
        );
        query.setParameter(1, userid);
        List<Object[]> objects = query.getResultList();
        List<Account> accounts = new ArrayList<>();
        for (Object[] obj : objects) {
            Account account = new Account();
            account.setAccountid(((BigInteger) obj[0]).longValue());
            account.setName((String) obj[1]);
            accounts.add(account);
        }
        return accounts;
    }

    @Override
    public List<Account> findAllByFamem(Famem famem) {
        return accountRepository.findAllByFamem(famem);
    }

    @Override
    public List<Account> findAllByUserSort(Long userid, Long accountid) {
        Query query = em.createNativeQuery(
                "select a.accountid, a.name \n" +
                        "from (select a.name, a.accountid, f.userid, \n" +
                        "\t\tcase \n" +
                        "\t\t\twhen accountid = ?\n" +
                        "\t\t\tthen 1 \n" +
                        "\t\t\telse row_number() over() + 1 \n" +
                        "\t\tend rn\n" +
                        "\tfrom account a join famem f on a.famemid = f.famemid where f.userid = ? and a.isclosesign = 0) a\n" +
                        "order by a.rn"
        );
        query.setParameter(1, accountid);
        query.setParameter(2, userid);
        List<Object[]> objects = query.getResultList();
        List<Account> accounts = new ArrayList<>();
        for (Object[] obj : objects) {
            Account account = new Account();
            account.setAccountid(((BigInteger) obj[0]).longValue());
            account.setName((String) obj[1]);
            accounts.add(account);
        }
        return accounts;
    }

    @Override
    public BigDecimal famemBalance(Famem famem) {
        return accountRepository.famemBalance(famem.getFamemid());
    }
}