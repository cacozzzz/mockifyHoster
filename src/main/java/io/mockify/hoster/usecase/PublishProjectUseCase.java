package io.mockify.hoster.usecase;

import io.mockify.hoster.dao.Repository;

public class PublishProjectUseCase implements UseCase<UseCaseRequest, Void> {

    private final Repository repository;

    public PublishProjectUseCase(Repository repository) {
        this.repository = repository;
    }

    @Override
    public Void execute(UseCaseRequest request) {
        if (request.getProject() == null ||
                request.getUserId() == null ||
                request.getHtml() == null) {
            throw new IllegalArgumentException("Mandatory attributes are missing");
        }

        repository.saveHtml(request.getHtml(), request.getProject(), request.getUserId());

        return null;
    }
}
