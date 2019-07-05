package com.mena.news.controllers;

import com.mena.news.models.News;
import com.mena.news.services.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/news")
public class NewsRestController {
    private final NewsService service;

    @Autowired
    public NewsRestController(NewsService service) {
        this.service = service;
    }

    @GetMapping
    public List<News> getAll() {
        return service.getAll();
    }

    @PostMapping
    public News create(@RequestBody @Valid News news) {
        return service.create(news);
    }

    @PutMapping
    public News update(@RequestBody @Valid News news) {
        try {
            return service.edit(news);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("not found with id %d ", news.getId()));
        }
    }

    @DeleteMapping
    public void delete(@RequestBody @Valid News news) {
        try {
            service.delete(news);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("News with id %d was not found ", news.getId()));
        }
    }

    @GetMapping("/{id}")
    public News getById(@PathVariable int id) {
        try {
            return service.getById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("News with id %d was not found ", id));
        }
    }

    @GetMapping("/filter/date/{date}")
    public List<News> filterByDate(@PathVariable String date) {
        return service.filterByDate(date);
    }

    @GetMapping("/filter/title/{title}")
    public List<News> filterByTitle(@PathVariable String title) {
        return service.filterByTitle(title);
    }

    @GetMapping("/filter/dateTitle/{date}/{title}")
    public List<News> filterByDateAndTitle(@PathVariable String date, @PathVariable String title) {
        return service.filterByTitleAndDate(date, title);
    }

    @GetMapping("/sort/title")
    public List<News> orderByTitle() {
        return service.sortByTitle();
    }
    @GetMapping("/sort/titleAsc")
    public List<News> orderByTitleAsc() {
        List<News> tempList = service.sortByTitle();
        Collections.reverse(tempList);
        return tempList;
    }

    @GetMapping("/sort/date")
    public List<News> orderByDate() {
        return service.sortByDate();
    }
    @GetMapping("/sort/dateAsc")
    public List<News> orderByDateAsc() {
        List<News> tempList = service.sortByDate();
         Collections.reverse(tempList);
         return tempList;
    }

    @GetMapping("/sort/dateTitle")
    public List<News> orderByDateAndTitle() {
        return service.sortByDateAndTitle();
    }

}
