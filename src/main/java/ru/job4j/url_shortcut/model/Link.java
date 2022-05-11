package ru.job4j.url_shortcut.model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Table(name = "link")
@Getter
@Setter
public class Link {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String url;
    private String code;
    private int count;

    @Transient
    private boolean isCorrectUrl;

    @ManyToOne()
    @JoinColumn(name = "account_id")
    private Account account;

    public Link() {
    }

    public Link(String url, Account account) {
        this.url = url;
        this.account = account;
    }
}
