package com.scalefocus.pms.mappers;

import com.scalefocus.pms.constants.TestConstants;
import com.scalefocus.pms.domain.Message;
import com.scalefocus.pms.models.binding.MessageBindingModel;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MessageMapperTest {

    private MessageBindingModel bindingModel;
    private MessageMapper messageMapper;
    private CycleAvoidingMappingContext context;

    @Before
    public void setUp() throws Exception {
        bindingModel = new MessageBindingModel();
        messageMapper = MessageMapper.INSTANCE;
        context = new CycleAvoidingMappingContext();
    }

    @Test
    public void messageToModelTest() {
        Message message = new Message();
        message.setReceiver(TestConstants.TEST_STRING);

        bindingModel = messageMapper.messageToModel(message, context);

        assertEquals(bindingModel.getReceiver(), TestConstants.TEST_STRING);
    }

    @Test
    public void modelToMessage() {
        bindingModel.setContent(TestConstants.TEST_STRING);

        Message message = messageMapper.modelToMessage(bindingModel, context);

        assertEquals(message.getContent(), TestConstants.TEST_STRING);
    }
}