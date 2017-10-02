package io.mockify.hoster.view;

import io.mockify.hoster.model.Project;
import org.springframework.stereotype.Service;

@Service
public class ProjectViewModelBuilder {

    public ProjectViewModel build(Project project) {
        ProjectViewModel projectViewModel = new ProjectViewModel();
        projectViewModel.projectId = project.getId();
        projectViewModel.projectName = project.getName();
        projectViewModel.template = project.getTemplate().getHTMLdata();
        projectViewModel.postCount = project.getPostsList().size();

        return projectViewModel;
    }
}
