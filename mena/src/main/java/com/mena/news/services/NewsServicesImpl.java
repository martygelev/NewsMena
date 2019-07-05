package com.mena.news.services;

import com.mena.news.models.News;
import com.mena.news.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class NewsServicesImpl implements NewsService {

    private final NewsRepository repository;

    @Autowired
    public NewsServicesImpl(NewsRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<News> getAll() {
        return repository.findAll();
    }

    @Override
    public News getById(int id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                String.format("No news with id %d.", id)));
    }

    @Override
    public News create(News news) {
        if (news == null)
            throw new ResponseStatusException(HttpStatus.CONFLICT, "News is null.");
        return repository.save(news);
    }

    @Override
    public News edit(News news) {
        if (news == null || getById(news.getId()) == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "News not found.");
        return repository.save(news);
    }

    @Override
    public void delete(News news) {
        if (news == null || getById(news.getId()) == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "News not found.");
        repository.delete(news);
    }

    @Override
    public List<News> sortByTitle() {
        return repository.findAllByOrderByTitle();
    }


    @Override
    public List<News> sortByDate() {
        return repository.findAllByOrderByDate();
    }


    @Override
    public List<News> sortByDateAndTitle() {
        return repository.findAllByOrderByTitleAscDateAsc();
    }

    @Override
    public List<News> filterByTitle(String title) {
        return repository.findAllByTitle(title);
    }

    @Override
    public List<News> filterByDate(String date) {
        return repository.findAllByDate(date);
    }

    @Override
    public List<News> filterByTitleAndDate(String date, String title) {
        return repository.findAllByDateAndTitle(date, title);
    }

}
