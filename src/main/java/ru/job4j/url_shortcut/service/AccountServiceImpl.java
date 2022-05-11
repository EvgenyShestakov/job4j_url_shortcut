package ru.job4j.url_shortcut.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.url_shortcut.model.Account;
import ru.job4j.url_shortcut.repository.AccountRepository;

import static java.util.Collections.emptyList;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    private final BCryptPasswordEncoder encoder;

    public AccountServiceImpl(AccountRepository accountRepository, BCryptPasswordEncoder encoder) {
        this.accountRepository = accountRepository;
        this.encoder = encoder;
    }

    @Override
    public Account findAccountBySite(String site) {
        return accountRepository.findAccountBySite(site);
    }

    @Override
    public Account findAccountByLogin(String login) {
        return accountRepository.findAccountByLogin(login);
    }

    @Override
    public Account save(Account account) {
        Account foundAccount = findAccountBySite(account.getSite());
        if (foundAccount == null) {
            String login = RandomStringUtils.randomAlphanumeric(8);
            String password = RandomStringUtils.randomAlphanumeric(8);
            account.setLogin(login);
            account.setPassword(encoder.encode(password));
            Account savedAccount = accountRepository.save(account);
            savedAccount.setRegistration(true);
            savedAccount.setPassword(password);
            foundAccount = savedAccount;
        }
        return foundAccount;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account user = accountRepository.findAccountByLogin(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new User(user.getLogin(), user.getPassword(), emptyList());
    }
}
