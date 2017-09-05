package io.mockify.hoster.usecase;

import io.mockify.hoster.dao.Repository;
import io.mockify.hoster.model.Project;

public class LoadProjectUseCase implements UseCase<UseCaseRequest, Project> {

    private final Repository repository;

    public LoadProjectUseCase(Repository repository) {
        this.repository = repository;
    }

    /**
     *
     * @param request
     * @return
     * @throws IllegalAccessException when mandatory attributes are missing.
     */
    @Override
    public Project execute(UseCaseRequest request) {
        if (request.getProjectName() == null ||
                request.getUserId() == null) {
            throw new IllegalArgumentException("Mandatory attributes are missing");
        }

        return repository.load(request.getProjectName(), request.getUserId());
    }
}
