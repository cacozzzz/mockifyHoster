package io.mockify.hoster.usecase;

import io.mockify.hoster.dao.Repository;
import io.mockify.hoster.model.Project;

public class SaveProjectUseCase implements UseCase<UseCaseRequest, Void> {
    private final Repository repository;

    public SaveProjectUseCase(Repository repository) {
        this.repository = repository;
    }

    @Override
    public Void execute(UseCaseRequest request) {
        Project project = request.getProject();

        if (project == null ||
                project.getName() == null ||
                request.getUserId() == null) {
            throw new IllegalArgumentException("Mandatory attributes are missing");
        }

        repository.save(request.getProject(), request.getUserId());

        return null;
    }
}
