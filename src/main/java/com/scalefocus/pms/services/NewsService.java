// All Rights Reserved, Copyright © ScaleFocus

package com.scalefocus.pms.services;

import com.scalefocus.pms.domain.News;
import com.scalefocus.pms.models.binding.NewsBindingModel;

import java.util.List;

public interface NewsService {
    News saveOrUpdate(NewsBindingModel bindingModel);

    List<NewsBindingModel> getAllNewsBindingModels();

    void deleteNews(String id);

    NewsBindingModel getNewsBindingModel(String id);
}
