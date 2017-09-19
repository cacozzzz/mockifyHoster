package io.mockify.hoster.usecase;

import io.mockify.hoster.gateways.RemoteHtmlGateway;
import io.mockify.hoster.model.Project;
import io.mockify.hoster.model.Template;

public class LoadRemoteTemplateFromUrlUseCase implements UseCase<UseCaseRequest, Void> {
    private final RemoteHtmlGateway remoteHtmlGateway;

    public LoadRemoteTemplateFromUrlUseCase(RemoteHtmlGateway remoteHtmlGateway) {
        this.remoteHtmlGateway = remoteHtmlGateway;
    }

    @Override
    public Void execute(UseCaseRequest useCaseRequest) {
        Project project = useCaseRequest.getProject();
        Template template = new Template();
        template.setId(0);
        template.setName("Remote template");

        byte[] htmlBytes = remoteHtmlGateway.get(useCaseRequest.getRemoteTemplateUrl());

        template.setHTMLdata(new String(htmlBytes));
        project.setTemplate(template);
        return null;
    }
}
