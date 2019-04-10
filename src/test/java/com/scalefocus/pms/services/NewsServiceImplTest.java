package com.scalefocus.pms.services;

import com.scalefocus.pms.constants.FieldConstants;
import com.scalefocus.pms.constants.TestConstants;
import com.scalefocus.pms.domain.News;
import com.scalefocus.pms.models.binding.NewsBindingModel;
import com.scalefocus.pms.repositories.NewsRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class NewsServiceImplTest {
    private NewsService newsService;

    @Mock
    private NewsRepository newsRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        newsService = new NewsServiceImpl(newsRepository);
    }

    @Test
    public void saveOrUpdateSuccessfulTest() {
        NewsBindingModel bindingModel = new NewsBindingModel();

        bindingModel.setTitle(FieldConstants.BINDING_MODEL_STRING);
        bindingModel.setContent(FieldConstants.BINDING_MODEL_STRING);

        News news = newsService.saveOrUpdate(bindingModel);

        assertEquals(FieldConstants.BINDING_MODEL_STRING, news.getTitle());
        assertEquals(FieldConstants.BINDING_MODEL_STRING, news.getContent());
    }

    @Test
    public void getAllNewsBindingModelsTest(){
        List<News> news = new ArrayList<>();
        News testNews = new News();
        testNews.setContent(TestConstants.TEST_STRING);
        news.add(testNews);

        when(newsRepository.findAll()).thenReturn(news);

        List<NewsBindingModel> newsBindingModels = newsService.getAllNewsBindingModels();
        
        assertEquals(newsBindingModels.get(0).getContent(), testNews.getContent());
    }

 
    @Test
    public void editNewsTest(){
        News news = new News();
        news.setContent(TestConstants.TEST_STRING);

        when(this.newsRepository.findById(anyLong())).thenReturn(Optional.of(news));

        NewsBindingModel bindingModel = newsService.getNewsBindingModel(TestConstants.TEST_STRING_ID);

        assertEquals(bindingModel.getContent(), news.getContent());
    }

}