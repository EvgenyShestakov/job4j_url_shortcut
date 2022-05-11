package ru.job4j.url_shortcut.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.job4j.url_shortcut.dto.*;
import ru.job4j.url_shortcut.model.Account;
import ru.job4j.url_shortcut.model.Link;
import ru.job4j.url_shortcut.service.AccountService;
import ru.job4j.url_shortcut.service.LinkService;
import javax.validation.Valid;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class AccountController {
    private AccountService accountService;
    private LinkService linkService;

    public AccountController(AccountService accountService, LinkService linkService) {
        this.accountService = accountService;
        this.linkService = linkService;
    }

    @GetMapping("/redirect/{code}")
    public ResponseEntity<LinkDtoUrl> redirect(@PathVariable String code) {
        Optional<Link> optionalLink = linkService.findByCode(code);
        Link link = optionalLink.orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Url is not found"));
        return new ResponseEntity<>(new LinkDtoUrl(link.getUrl()), HttpStatus.FOUND);
    }

    @GetMapping("/statistic")
    public ResponseEntity<Set<LinkDtoUrlTotal>> statistic() {
        String login = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Account account = accountService.findAccountByLogin(login);
        Set<LinkDtoUrlTotal> linkDtoUrlTotals =  account.getLinks().stream().map(link -> new LinkDtoUrlTotal(link.
                getUrl(), link.getCount())).collect(Collectors.toSet());
        return new ResponseEntity<>(linkDtoUrlTotals, HttpStatus.OK);
    }

    @PostMapping("/registration")
    public ResponseEntity<AccountDtoFull> registration(@Valid @RequestBody AccountDtoSite accountDtoSite) {
        Account savedAccount = accountService.save(new Account(accountDtoSite.getSite()));
        return new ResponseEntity<>(new AccountDtoFull(savedAccount.getLogin(), savedAccount.getPassword(),
                savedAccount.getSite(), savedAccount.isRegistration()), HttpStatus.OK);
    }

    @PostMapping("/convert")
    public ResponseEntity<LinkDtoCode> convert(@Valid @RequestBody LinkDtoUrl linkDtoUrl) {
        String login = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Account account = accountService.findAccountByLogin(login);
        Link link = linkService.save(new Link(linkDtoUrl.getUrl(), account));
        return new ResponseEntity<>(new LinkDtoCode(link.getCode()), link.isCorrectUrl() ?
                HttpStatus.CREATED : HttpStatus.BAD_REQUEST);
    }
}
