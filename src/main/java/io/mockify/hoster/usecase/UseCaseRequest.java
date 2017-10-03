package io.mockify.hoster.usecase;

import io.mockify.hoster.model.Project;

public class UseCaseRequest {

    private Project project;
    private String userId;
    private String html;
    private String projectName;
    private String remoteTemplateUrl;

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getRemoteTemplateUrl() {
        return remoteTemplateUrl;
    }

    public void setRemoteTemplateUrl(String remoteTemplateUrl) {
        this.remoteTemplateUrl = remoteTemplateUrl;
    }
}
