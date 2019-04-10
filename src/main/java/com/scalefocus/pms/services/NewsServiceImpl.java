// All Rights Reserved, Copyright © ScaleFocus

package com.scalefocus.pms.services;

import com.scalefocus.pms.domain.News;
import com.scalefocus.pms.exceptions.CustomNotfoundException;
import com.scalefocus.pms.mappers.CycleAvoidingMappingContext;
import com.scalefocus.pms.mappers.NewsMapper;
import com.scalefocus.pms.models.binding.NewsBindingModel;
import com.scalefocus.pms.repositories.NewsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {

    private final NewsRepository newsRepository;
    private final NewsMapper newsMapper;
    private final CycleAvoidingMappingContext context;

    /**
     * @param newsRepository - News repository.
     */
    @Autowired
    public NewsServiceImpl(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
        this.newsMapper = NewsMapper.INSTANCE;
        context = new CycleAvoidingMappingContext();
    }

    /**
     * @param bindingModel - The news binding model.
     * @return - returns news entity.
     */
    @Override
    public News saveOrUpdate(NewsBindingModel bindingModel) {
        News news = newsMapper.modelToNews(bindingModel,context);

        newsRepository.saveAndFlush(news);

        return news;
    }

    /**
     *
     * @return - returns a list of all news binding models.
     */
    @Override
    public List<NewsBindingModel> getAllNewsBindingModels() {
        List<NewsBindingModel> newsModels = new ArrayList<>();
        List<News> newsList = this.newsRepository.findAll();

        for (News news : newsList) {
            newsModels.add(newsMapper.newsToModel(news,context));
        }

        return newsModels;
    }

    /**
     *
     * @param id - The news id.
     */
    @Override
    public void deleteNews(String id) {
        try {
            this.newsRepository.deleteById(Long.valueOf(id));
        } catch (IllegalArgumentException e) {
            throw new CustomNotfoundException("News could not be found", e);
        }
    }

    /**
     *
     * @param id - News id.
     * @return - returns a news Binding model
     */
    @Override
    public NewsBindingModel getNewsBindingModel(String id) {
        News news = this.newsRepository.findById(Long.valueOf(id)).orElse(null);

        if (news == null) {
            throw new CustomNotfoundException("News could not be found");
        }

        return newsMapper.newsToModel(news,context);
    }
}
