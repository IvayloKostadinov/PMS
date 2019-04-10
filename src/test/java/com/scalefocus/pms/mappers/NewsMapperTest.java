package com.scalefocus.pms.mappers;

import com.scalefocus.pms.constants.TestConstants;
import com.scalefocus.pms.domain.News;
import com.scalefocus.pms.models.binding.NewsBindingModel;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NewsMapperTest {
    private NewsBindingModel bindingModel;
    private NewsMapper newsMapper;
    private CycleAvoidingMappingContext context;

    @Before
    public void setUp() throws Exception {
        bindingModel = new NewsBindingModel();
        newsMapper = NewsMapper.INSTANCE;
        context = new CycleAvoidingMappingContext();
    }

    @Test
    public void newsToModelTest() {
        News news = new News();
        news.setContent(TestConstants.TEST_STRING);

        bindingModel = newsMapper.newsToModel(news, context);

        assertEquals(bindingModel.getContent(), TestConstants.TEST_STRING);
    }

    @Test
    public void modelToNewsTest() {
        bindingModel.setContent(TestConstants.TEST_STRING);

        News news = newsMapper.modelToNews(bindingModel, context);

        assertEquals(news.getContent(), TestConstants.TEST_STRING);
    }
}