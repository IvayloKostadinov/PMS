// All Rights Reserved, Copyright © ScaleFocus

package com.scalefocus.pms.models.binding;

public class DocumentationBindingModel {
    private Long id;
    private ProjectBindingModel project;
    private UserBindingModel creator;
    private String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProjectBindingModel getProject() {
        return project;
    }

    public void setProject(ProjectBindingModel project) {
        this.project = project;
    }

    public UserBindingModel getCreator() {
        return creator;
    }

    public void setCreator(UserBindingModel creator) {
        this.creator = creator;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
