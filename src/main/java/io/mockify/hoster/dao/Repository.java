package io.mockify.hoster.dao;

import io.mockify.hoster.exceptions.persistence.NullProjectRepositoryException;
import io.mockify.hoster.model.Project;

import java.util.List;

public interface Repository{
    Project load(String projectName, String userId);

    List<Project> loadAllByUserId(String userId);

    void save(Project project, String userId) throws NullProjectRepositoryException;

    void saveHtml(String html , Project project, String userId);
}
