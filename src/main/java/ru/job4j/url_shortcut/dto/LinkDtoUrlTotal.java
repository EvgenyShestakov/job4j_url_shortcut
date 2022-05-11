package ru.job4j.url_shortcut.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LinkDtoUrlTotal {
    private String url;
    private int total;

    public LinkDtoUrlTotal() {
    }

    public LinkDtoUrlTotal(String url, int total) {
        this.url = url;
        this.total = total;
    }
}
