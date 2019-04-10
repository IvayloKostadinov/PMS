package com.scalefocus.pms.mappers;

import com.scalefocus.pms.constants.TestConstants;
import com.scalefocus.pms.domain.Documentation;
import com.scalefocus.pms.models.binding.DocumentationBindingModel;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DocumentationMapperTest {

    private DocumentationBindingModel bindingModel;
    private DocumentationMapper documentationMapper;
    private CycleAvoidingMappingContext context;

    @Before
    public void setUp() throws Exception {
        bindingModel = new DocumentationBindingModel();
        documentationMapper = DocumentationMapper.INSTANCE;
        context = new CycleAvoidingMappingContext();
    }

    @Test
    public void documentationToModelTest() {
        Documentation documentation = new Documentation();
        documentation.setContent(TestConstants.TEST_STRING);

        bindingModel = documentationMapper.documentationToModel(documentation, context);

        assertEquals(bindingModel.getContent(), TestConstants.TEST_STRING);
    }

    @Test
    public void modelToDocumentationTest() {
        bindingModel.setContent(TestConstants.TEST_STRING);

        Documentation documentation = documentationMapper.modelToDocumentation(bindingModel, context);

        assertEquals(documentation.getContent(), TestConstants.TEST_STRING);
    }
}