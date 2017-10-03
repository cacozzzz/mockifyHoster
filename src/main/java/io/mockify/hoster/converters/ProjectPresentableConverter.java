package io.mockify.hoster.converters;

import io.mockify.hoster.model.Project;
import io.mockify.hoster.view.ProjectPresentable;
import org.springframework.stereotype.Component;

@Component
public class ProjectPresentableConverter implements Converter<Project, ProjectPresentable> {

    private PostPresentableConverter postPresentableConverter;
    private TemplatePresentableConverter templatePresentableConverter;
    private ResourcePresentableConverter resourcePresentableConverter;

    public ProjectPresentableConverter(PostPresentableConverter postPresentableConverter, TemplatePresentableConverter templatePresentableConverter, ResourcePresentableConverter resourcePresentableConverter) {
        this.postPresentableConverter = postPresentableConverter;
        this.templatePresentableConverter = templatePresentableConverter;
        this.resourcePresentableConverter = resourcePresentableConverter;
    }

    @Override
    public ProjectPresentable convertForth(Project from) {
        ProjectPresentable to = new ProjectPresentable();

        to.setId(from.getId());
        to.setName(from.getName());

        to.setTemplate(templatePresentableConverter.convertForth(from.getTemplate()));
        from.getResourceList().forEach(r -> to.setResource(resourcePresentableConverter.convertForth(r)));
        from.getPostsList().forEach(p -> to.setPost(postPresentableConverter.convertForth(p)));

        return to;
    }

    @Override
    public Project convertBack(ProjectPresentable from) {
        Project to = new Project();

        to.setId(from.getId());
        to.setName(from.getName());

        to.setTemplate(templatePresentableConverter.convertBack(from.getTemplate()));
        from.getResourceList().forEach(r -> to.setResource(resourcePresentableConverter.convertBack(r)));
        from.getPostsList().forEach(p -> to.setPost(postPresentableConverter.convertBack(p)));

        return to;
    }
}
