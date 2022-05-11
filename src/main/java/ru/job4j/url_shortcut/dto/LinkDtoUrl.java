package ru.job4j.url_shortcut.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

@Getter
@Setter
public class LinkDtoUrl {
    @URL(message = "Invalid URL address")
    private String url;

    public LinkDtoUrl() {
    }

    public LinkDtoUrl(@URL(message = "Invalid URL address") String url) {
        this.url = url;
    }
}
