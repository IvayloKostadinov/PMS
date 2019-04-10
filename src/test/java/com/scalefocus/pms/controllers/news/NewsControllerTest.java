package com.scalefocus.pms.controllers.news;

import com.scalefocus.pms.constants.AttributeConstants;
import com.scalefocus.pms.constants.MappingConstants;
import com.scalefocus.pms.constants.TestConstants;
import com.scalefocus.pms.constants.ViewNameConstants;
import com.scalefocus.pms.domain.News;
import com.scalefocus.pms.services.NewsService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class NewsControllerTest {

    @Mock
    private NewsService newsService;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        NewsController controller = new  NewsController(newsService);
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/templates/");
        viewResolver.setSuffix(".html");
        mockMvc = MockMvcBuilders.standaloneSetup(controller).setViewResolvers(viewResolver).build();
    }

    @Test
    public void getNewsFormSuccessfulTest() throws Exception{
        mockMvc.perform(get(MappingConstants.ADD_NEWS_FORM))
                .andExpect(view().name(ViewNameConstants.NEWS_CREATE))
                .andExpect(model().attributeExists(AttributeConstants.NEWS_MODEL));
    }

    @Test
    public void createNewsSuccessfulTest() throws Exception{
        when(newsService.saveOrUpdate(any())).thenReturn(new News());

        mockMvc.perform(post(MappingConstants.CREATE_NEWS))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name(ViewNameConstants.REDIRECT + ViewNameConstants.HOME));
    }

    @Test
    public void deleteNewsTest() throws Exception {
        mockMvc.perform(get(TestConstants.TEST_CREATE_URL))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name(ViewNameConstants.REDIRECT + ViewNameConstants.HOME));
    }

    @Test
    public void editNewsTest() throws Exception {
        mockMvc.perform(get(TestConstants.TEST_EDIT_URL))
        .andExpect(status().is2xxSuccessful())
        .andExpect(view().name(ViewNameConstants.NEWS_CREATE));
    }

}