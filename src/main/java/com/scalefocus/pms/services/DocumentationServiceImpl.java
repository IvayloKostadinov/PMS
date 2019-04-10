// All Rights Reserved, Copyright © ScaleFocus

package com.scalefocus.pms.services;


import com.scalefocus.pms.domain.Documentation;
import com.scalefocus.pms.mappers.CycleAvoidingMappingContext;
import com.scalefocus.pms.mappers.DocumentationMapper;
import com.scalefocus.pms.models.binding.DocumentationBindingModel;
import com.scalefocus.pms.models.binding.ProjectBindingModel;
import com.scalefocus.pms.repositories.DocumentationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentationServiceImpl implements DocumentationService {

    private final DocumentationRepository documentationRepository;
    private final DocumentationMapper documentationMapper;
    private final ProjectService projectService;
    private final CycleAvoidingMappingContext context;

    /**
     * @param documentationRepository - Documentation repository.
     * @param projectService          - Project service.
     */

    @Autowired
    public DocumentationServiceImpl(DocumentationRepository documentationRepository, ProjectService projectService) {
        this.documentationRepository = documentationRepository;
        this.projectService = projectService;
        this.context = new CycleAvoidingMappingContext();
        this.documentationMapper = DocumentationMapper.INSTANCE;
    }

    @Override
    public void saveDocumentation(DocumentationBindingModel bindingModel) {

        Documentation documentation = documentationMapper.modelToDocumentation(bindingModel, context);

        this.documentationRepository.saveAndFlush(documentation);

    }

    @Override
    public List<DocumentationBindingModel> findDocumentationByProjectId(String projectId) {
        ProjectBindingModel project = projectService.findModelById(projectId);
        List<DocumentationBindingModel> documentations = project.getDocumentations();
        if (documentations.isEmpty()) {
            DocumentationBindingModel bindingModel = new DocumentationBindingModel();
            bindingModel.setProject(project);
            documentations.add(bindingModel);
        }
        return documentations;

    }

}