package io.mockify.hoster.model.dao;

import io.mockify.hoster.model.Project;

public interface Repository {
    public Project load(String projectName);

    public void save(Project project);

    public void savePageToFile(String html , Project project);
}
