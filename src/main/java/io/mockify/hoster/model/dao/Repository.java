package io.mockify.hoster.model.dao;

import io.mockify.hoster.model.Project;

public interface Repository {
    Project load(String projectName);

    void save(Project project);

    void saveHtml(String html , Project project);
}
