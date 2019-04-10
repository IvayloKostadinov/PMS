package com.scalefocus.pms.formatters;

import com.scalefocus.pms.constants.TestConstants;
import com.scalefocus.pms.models.binding.UserBindingModel;
import com.scalefocus.pms.services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.text.ParseException;
import java.util.Locale;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class UserFormatterTest {

    @Mock
    private UserService userService;

    private UserFormatter formatter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.formatter = new UserFormatter(userService);
    }

    @Test
    public void parseTest() throws ParseException {
        UserBindingModel bindingModel = new UserBindingModel();
        bindingModel.setFamilyName(TestConstants.TEST_STRING);

        when(userService.getUserBindingModelById(TestConstants.TEST_ID)).thenReturn(bindingModel);

        assertEquals(formatter.parse(TestConstants.TEST_ID.toString(),new Locale("en")),bindingModel);

    }

    @Test
    public void printTest() {
        UserBindingModel bindingModel = new UserBindingModel();
        bindingModel.setId(TestConstants.TEST_ID);

        String id = formatter.print(bindingModel,new Locale("en"));

        assertEquals(id, bindingModel.getId().toString());
    }
}