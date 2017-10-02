package io.mockify.hoster.webapplication;

import io.mockify.hoster.dao.Repository;
import io.mockify.hoster.model.Project;
import io.mockify.hoster.view.ProjectViewModel;
import io.mockify.hoster.view.ProjectViewModelBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ViewController {

    private Security security;
    private Repository repository;
    private ProjectViewModelBuilder projectViewModelBuilder;

    public ViewController(Security security, Repository repository, ProjectViewModelBuilder projectViewModelBuilder) {
        this.security = security;
        this.repository = repository;
        this.projectViewModelBuilder = projectViewModelBuilder;
    }

    @GetMapping("/")
    public String userPage(Model model) {

        String userId = security.getUserId();
        List<Project> projects = repository.loadAllByUserId(userId);
        List<ProjectViewModel> projectViewModels = new ArrayList<>();
        projects.forEach(p -> projectViewModels.add(projectViewModelBuilder.build(p)));
        model.addAttribute("userId", userId);
        model.addAttribute("projects", projectViewModels);

        return "userPage";
    }

    @GetMapping("/{projectName}")
    public String projectPage(@PathVariable("projectName") String projectName, Model model) {
        String userId = security.getUserId();
        Project project = repository.load(projectName, userId);

        model.addAttribute("userId", userId);
        model.addAttribute("project", projectViewModelBuilder.build(project));

        return "projectPage";
    }
}
