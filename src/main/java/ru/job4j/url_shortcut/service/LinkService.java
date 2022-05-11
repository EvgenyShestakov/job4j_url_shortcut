package ru.job4j.url_shortcut.service;

import ru.job4j.url_shortcut.model.Account;
import ru.job4j.url_shortcut.model.Link;

import java.util.Optional;
import java.util.Set;

public interface LinkService {
    Link save(Link link);
    Optional<Link> findByCode(String code);
    Set<Link> findByAccount(Account account);
}
