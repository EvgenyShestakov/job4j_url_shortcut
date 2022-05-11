package ru.job4j.url_shortcut.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountDtoFull {
    private String login;
    private String password;
    private String site;
    private boolean registration;

    public AccountDtoFull() {
    }

    public AccountDtoFull(String login, String password, String site, boolean registration) {
        this.login = login;
        this.password = password;
        this.site = site;
        this.registration = registration;
    }
}
