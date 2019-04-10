// All Rights Reserved, Copyright © ScaleFocus

package com.scalefocus.pms.controllers.news;

import com.scalefocus.pms.constants.AttributeConstants;
import com.scalefocus.pms.constants.MappingConstants;
import com.scalefocus.pms.constants.ViewNameConstants;
import com.scalefocus.pms.controllers.BaseController;
import com.scalefocus.pms.models.binding.NewsBindingModel;
import com.scalefocus.pms.services.NewsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NewsController extends BaseController {

    private final NewsService newsService;

    /**
     * @param newsService - The service class for the News entity.
     */
    @Autowired
    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    /**
     * This method gets the about us page.
     *
     * @return - returns the view for contact us page.
     */
    @GetMapping(MappingConstants.NEWS_SHOW)
    public ModelAndView getNews(ModelAndView model) {
        model.addObject(AttributeConstants.NEWS_LIST, newsService.getAllNewsBindingModels());

        return this.view(ViewNameConstants.SHOW_NEWS, model);
    }

    /**
     * @param model - Model and View for the page.
     * @return - returns the view for creating news.
     */
    @GetMapping(MappingConstants.ADD_NEWS_FORM)
    public ModelAndView getNewsForm(ModelAndView model) {
        model.addObject(AttributeConstants.NEWS_MODEL, new NewsBindingModel());

        return this.view(ViewNameConstants.NEWS_CREATE, model);
    }

    /**
     * @param bindingModel - The news binding model.
     * @param model        - Model and View for the page.
     * @return - returns redirected view to all news.
     */
    @PostMapping(MappingConstants.CREATE_NEWS)
    public ModelAndView saveOrUpdate(@ModelAttribute NewsBindingModel bindingModel, ModelAndView model) {
        newsService.saveOrUpdate(bindingModel);

        return this.redirect(ViewNameConstants.NEWS_SHOW);
    }

    /**
     * @param id    - The news id.
     * @param model - Model and view for the page.
     * @return - returns redirected view.
     */
    @GetMapping(MappingConstants.DELETE_NEWS)
    public ModelAndView deleteNews(@PathVariable(value = "id") String id, ModelAndView model) {
        newsService.deleteNews(id);

        return this.redirect(ViewNameConstants.NEWS_SHOW);
    }

    /**
     * @param id    - The news id.
     * @param model - Model and view for the page.
     * @return - returns redirected view.
     */
    @GetMapping(MappingConstants.EDIT_NEWS)
    public ModelAndView editNews(@PathVariable(value = "id") String id, ModelAndView model) {
        NewsBindingModel bindingModel = newsService.getNewsBindingModel(id);
        model.addObject(AttributeConstants.NEWS_MODEL, bindingModel);

        return this.view(ViewNameConstants.NEWS_CREATE, model);
    }
}
