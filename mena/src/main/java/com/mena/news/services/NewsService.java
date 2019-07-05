package com.mena.news.services;

import com.mena.news.models.News;

import java.util.List;

public interface NewsService {
    List<News> getAll();

    News getById(int id);

    News create(News news);

    News edit (News news);

    void delete(News news);

    List<News> sortByTitle();

    List<News> sortByDate();

    List<News> sortByDateAndTitle();

    List<News> filterByTitle(String title);

    List<News> filterByDate(String date);

    List<News> filterByTitleAndDate(String date, String title);


}
