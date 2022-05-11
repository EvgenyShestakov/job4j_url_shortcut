package ru.job4j.url_shortcut.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LinkDtoCode {
    private String code;

    public LinkDtoCode() {
    }

    public LinkDtoCode(String code) {
        this.code = code;
    }
}
