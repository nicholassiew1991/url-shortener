package com.github.nicholas1991.urlshortener.webapi.links.services.implementations;

import com.github.nicholas1991.urlshortener.webapi.tasks.constants.TaskNameConstants;
import com.github.nicholas1991.urlshortener.webapi.dataaccess.entities.Link;
import com.github.nicholas1991.urlshortener.webapi.dataaccess.entities.RedirectRecord;
import com.github.nicholas1991.urlshortener.webapi.dataaccess.repositories.LinkRepository;
import com.github.nicholas1991.urlshortener.webapi.dataaccess.repositories.RedirectRecordRepository;
import com.github.nicholas1991.urlshortener.webapi.links.mappers.LinkMapper;
import com.github.nicholas1991.urlshortener.webapi.links.mappers.RedirectRecordMapper;
import com.github.nicholas1991.urlshortener.webapi.links.models.CreateRedirectRecordTaskDataModel;
import com.github.nicholas1991.urlshortener.webapi.links.services.LinkCodeGenerator;
import com.github.nicholas1991.urlshortener.webapi.links.services.LinkService;
import com.github.nicholas1991.urlshortener.webapi.tasks.producers.TaskProducer;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class LinkServiceImpl implements LinkService {

  private final LinkRepository linkRepository;

  private final RedirectRecordRepository redirectRecordRepository;

  private final LinkCodeGenerator linkCodeGenerator;

  private final TaskProducer taskProducer;

  private final LinkMapper linkMapper;

  private final RedirectRecordMapper redirectRecordMapper;

  private final Logger logger;

  public LinkServiceImpl(LinkRepository linkRepository,
                         RedirectRecordRepository redirectRecordRepository,
                         LinkCodeGenerator linkCodeGenerator,
                         TaskProducer taskProducer,
                         LinkMapper linkMapper,
                         RedirectRecordMapper redirectRecordMapper,
                         Logger logger) {
    this.linkRepository = linkRepository;
    this.redirectRecordRepository = redirectRecordRepository;
    this.linkCodeGenerator = linkCodeGenerator;
    this.taskProducer = taskProducer;
    this.linkMapper = linkMapper;
    this.redirectRecordMapper = redirectRecordMapper;
    this.logger = logger;
  }

  @Override
  @Cacheable(cacheManager = "redisCacheManager", cacheNames = "originalUrl", key = "#code")
  public Optional<String> getOriginalUrl(String code) {
    this.logger.info("Get Original Url with Code: " + code);
    return code == null || code.isBlank() == true ? Optional.empty() : this.linkRepository.findById(code).map(Link::getOriginalUrl);
  }

  @Override
  public Link create(String originalUrl) {
    String code = this.linkCodeGenerator.generate(5);
    Link link = this.linkMapper.create(code, originalUrl);
    return this.linkRepository.insert(link);
  }

  @Override
  public void createRedirectRecordTask(String code, Map<String, String> headers, Map<String, String> queryString) {
    CreateRedirectRecordTaskDataModel data = CreateRedirectRecordTaskDataModel.of(code, queryString, headers.get("referer"), headers.get("user-agent"));
    this.taskProducer.produce(TaskNameConstants.CREATE_REDIRECT_RECORD, data);
  }

  @Override
  public void createRedirectRecord(CreateRedirectRecordTaskDataModel data) {
    RedirectRecord record = this.redirectRecordMapper.create(ObjectId.get(), data);
    this.redirectRecordRepository.insert(record);
  }

}
