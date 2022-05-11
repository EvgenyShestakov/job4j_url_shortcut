package ru.job4j.url_shortcut.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import ru.job4j.url_shortcut.model.Account;

public interface AccountRepository extends CrudRepository<Account, Integer> {
    @EntityGraph(attributePaths = "links")
    Account findAccountBySite(String site);

    @EntityGraph(attributePaths = "links")
    Account findAccountByLogin(String login);
}
