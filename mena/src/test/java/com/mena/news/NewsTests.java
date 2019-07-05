package com.mena.news;

import com.mena.news.models.News;
import com.mena.news.repository.NewsRepository;
import com.mena.news.services.NewsServicesImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class NewsTests {

    @Mock
    NewsRepository newsRepository;

    @InjectMocks
    NewsServicesImpl service;

    private News news = new News();
    private News news2 = new News();
    private News news3 = new News();
    private List<News> list = new ArrayList<>();

    @Before
    public void setDefaultTestNews() {

        news.setId(1);
        news.setDate("2019-07-05");
        news.setTitle("testTitle");
        news.setShortDescription("desc");
        news.setText("short description of the news");

        news2.setId(2);
        news2.setDate("2019-09-05");
        news2.setTitle("testTitle2");
        news2.setShortDescription("desc2");
        news2.setText("short description of the news2");
        list.add(news);
        list.add(news2);

        news3.setId(3);
        news3.setDate("2059-07-05");
        news3.setTitle("testTitleSave");
        news3.setShortDescription("descSave");
        news3.setText("short description of the newsTEST SAVE");
    }


    @Test
    public void getAllNews_shouldReturnTwoNews_whenTwoNewsAreAvailable()  {
        // Arrange
        when(newsRepository.findAll())
                .thenReturn(list);

        // Act
        List<News> result = service.getAll();

        // Assert
        Assert.assertEquals(2, result.size());
        Assert.assertEquals("testTitle", result.get(0).getTitle());
        Assert.assertEquals("testTitle2", result.get(1).getTitle());
        verify(newsRepository, Mockito.times(1)).findAll();
    }

    @Test
    public void getAllDeleted_shouldInvokeFindAllByEnabledFalseInRepository()  {

        // Act
        List<News> result = service.getAll();

        // Assert
        verify(newsRepository, Mockito.times(1)).findAll();
    }

    @Test
    public void getNewsById_shouldReturnNews1_whenArgumentIsSetToOne()  {
        // Arrange
        when(newsRepository.findById(1))
                .thenReturn(java.util.Optional.ofNullable(news));

        // Act
        News result = service.getById(1);

        // Assert
        Assert.assertEquals("testTitle", result.getTitle());
        Assert.assertEquals(1,((long)result.getId()));
        verify(newsRepository, Mockito.times(1)).findById(1);
    }

    @Test
    public void addNews_shouldInvokeSaveInRepository_whenAddedSuccessfully() {

        // Act
        service.create(news3);

        // Assert
        verify(newsRepository, Mockito.times(1)).save(Mockito.any(News.class));
    }


    @Test
    public void editNews_shouldInvokeSaveInRepository_whenEditedSuccessfully() {

        //Arrange
        when(newsRepository.findById(1))
                .thenReturn(java.util.Optional.ofNullable(news));

        // Act
        service.edit(news);

        // Assert
        verify(newsRepository, Mockito.times(1)).save(Mockito.any(News.class));
    }

//    @Test(expected = ResponseStatusException.class)
//    public void editNews_shouldInvokeSaveInRepository_whenNewsEditedSuccessfully()  {
//        // Arrange
//        when(newsRepository.findAllByTitle(news.getTitle()))
//                .thenReturn(list);
//
//        // Act
//        news = service.edit(news);
//
//        // Assert
//        verify(newsRepository, Mockito.times(1)).save(Mockito.any(News.class));
//    }




}
