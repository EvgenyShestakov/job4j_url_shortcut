package ru.job4j.url_shortcut.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

@Getter
@Setter
public class AccountDtoSite {
    @URL(message = "Invalid URL address")
    private String site;

    public AccountDtoSite() {
    }

    public AccountDtoSite(@URL(message = "Invalid URL address") String site) {
        this.site = site;
    }
}
