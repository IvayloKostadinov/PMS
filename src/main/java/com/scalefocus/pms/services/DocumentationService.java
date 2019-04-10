// All Rights Reserved, Copyright © ScaleFocus

package com.scalefocus.pms.services;

import com.scalefocus.pms.models.binding.DocumentationBindingModel;

import java.util.List;

public interface DocumentationService {

    void saveDocumentation(DocumentationBindingModel bindingModel);

    List<DocumentationBindingModel> findDocumentationByProjectId(String projectId);

}
