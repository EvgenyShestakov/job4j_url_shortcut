package ru.job4j.url_shortcut.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.job4j.url_shortcut.model.Account;

public interface AccountService extends UserDetailsService {
    Account findAccountBySite(String site);
    Account findAccountByLogin(String login);
    Account save(Account account);
}
