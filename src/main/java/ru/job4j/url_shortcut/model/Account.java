package ru.job4j.url_shortcut.model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "account")
@Getter
@Setter
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String login;
    private String password;

    private String site;

    @Transient
    private boolean registration;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "account")
    private Set<Link> links = new HashSet<>();

    public Account() {
    }

    public Account(String site) {
        this.site = site;
    }
}
