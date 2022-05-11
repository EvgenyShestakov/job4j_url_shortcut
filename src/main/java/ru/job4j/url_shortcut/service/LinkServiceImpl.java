package ru.job4j.url_shortcut.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.url_shortcut.model.Account;
import ru.job4j.url_shortcut.model.Link;
import ru.job4j.url_shortcut.repository.LinkRepository;

import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class LinkServiceImpl implements LinkService {
    private LinkRepository linkRepository;

    public LinkServiceImpl(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    @Override
    public Link save(Link link) {
        Link savedLink;
        Link foundLink = linkRepository.findByUrl(link.getUrl());
        String site = link.getAccount().getSite();
        int index = site.lastIndexOf("/");
        if (foundLink == null && link.getUrl().contains(site.substring(index + 1))) {
            String code = RandomStringUtils.randomAlphanumeric(8);
            link.setCode(code);
            savedLink = linkRepository.save(link);
            savedLink.setCorrectUrl(true);
        } else if (foundLink != null) {
            savedLink = foundLink;
        } else {
            link.setCode("Incorrect url");
            savedLink = link;
        }
        return savedLink;
    }

    @Override
    public Optional<Link> findByCode(String code) {
        Optional<Link> linkOptional = linkRepository.findByCode(code);
        linkOptional.ifPresent(link -> linkRepository.updateCount(link.getId()));
        return linkOptional;
    }

    @Override
    public Set<Link> findByAccount(Account account) {
        return linkRepository.findByAccount(account);
    }
}
