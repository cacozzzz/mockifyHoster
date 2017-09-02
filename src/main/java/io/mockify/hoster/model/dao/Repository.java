package io.mockify.hoster.model.dao;

import io.mockify.hoster.model.Project;

public interface Repository {
    Project load(String projectName, String userId);

    void save(Project project, String userId);

    void saveHtml(String html , Project project, String userId);
}
