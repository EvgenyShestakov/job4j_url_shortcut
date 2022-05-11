package ru.job4j.url_shortcut.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.job4j.url_shortcut.model.Account;
import ru.job4j.url_shortcut.model.Link;
import java.util.Optional;
import java.util.Set;

public interface LinkRepository extends CrudRepository<Link, Integer> {
    @EntityGraph(attributePaths = "account")
    Link findByUrl(String url);

    @EntityGraph(attributePaths = "account")
    Optional<Link> findByCode(String code);

    @Modifying
    @Query("update Link l set l.count = l.count + 1 where l.id = :id")
    void updateCount(@Param("id") int id);

    Set<Link> findByAccount(Account account);
}
