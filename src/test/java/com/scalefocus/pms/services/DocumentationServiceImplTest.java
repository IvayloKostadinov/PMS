package com.scalefocus.pms.services;

import com.scalefocus.pms.models.binding.DocumentationBindingModel;
import com.scalefocus.pms.repositories.DocumentationRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;

public class DocumentationServiceImplTest {
    private DocumentationService documentationService;
    private ProjectService projectService;


    @Mock
    private DocumentationRepository documentationRepository;


    @Test
    public void saveDocumentation() {
    }

    @Test
    public void findDocumentationByProjectId() {
    }
}