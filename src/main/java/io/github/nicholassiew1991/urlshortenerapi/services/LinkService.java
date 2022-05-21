package io.github.nicholassiew1991.urlshortenerapi.services;

import io.github.nicholassiew1991.urlshortenerapi.models.Link;
import io.github.nicholassiew1991.urlshortenerapi.models.RedirectLinkModel;

import java.util.Optional;

public interface LinkService {

  Optional<RedirectLinkModel> getLink(String code);

  Link create(String originalUrl, String domain);
}
