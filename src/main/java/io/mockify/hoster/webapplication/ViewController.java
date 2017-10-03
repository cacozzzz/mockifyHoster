package io.mockify.hoster.webapplication;

import io.mockify.hoster.converters.ProjectPresentableConverter;
import io.mockify.hoster.dao.Repository;
import io.mockify.hoster.model.Project;
import io.mockify.hoster.view.ProjectPresentable;
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
    private ProjectPresentableConverter projectPresentableConverter;

    public ViewController(Security security, Repository repository, ProjectPresentableConverter projectPresentableConverter) {
        this.security = security;
        this.repository = repository;
        this.projectPresentableConverter = projectPresentableConverter;
    }

    @GetMapping("/")
    public String userPage(Model model) {

        String userId = security.getUserId();
        List<Project> projects = repository.loadAllByUserId(userId);
        List<ProjectPresentable> projectPresentables = new ArrayList<>();
        projects.forEach(p -> projectPresentables.add(projectPresentableConverter.convertForth(p)));
        model.addAttribute("userId", userId);
        model.addAttribute("projects", projectPresentables);

        return "userPage";
    }

    @GetMapping("/{projectName}/")
    public String projectPage(@PathVariable("projectName") String projectName, Model model) {
        String userId = security.getUserId();
        Project project = repository.load(projectName, userId);

        model.addAttribute("userId", userId);
        model.addAttribute("project", projectPresentableConverter.convertForth(project));

        return "projectPage";
    }
}
