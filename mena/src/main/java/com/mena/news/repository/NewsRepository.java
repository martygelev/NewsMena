package com.mena.news.repository;

import com.mena.news.models.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface  NewsRepository extends JpaRepository<News, Integer> {

    List<News> findAllByDate(String date);

    List<News> findAllByTitle(String title);

    List<News> findAllByDateAndTitle(String date, String title);

    List<News> findAllByOrderByDate();

    List<News> findAllByOrderByTitle();

    List<News> findAllByOrderByTitleAscDateAsc();

}
