package io.mockify.hoster.webapplication;

import io.mockify.hoster.dao.Repository;
import io.mockify.hoster.model.Project;
import io.mockify.hoster.usecase.LoadProjectUseCase;
import io.mockify.hoster.usecase.ProjectCompilerUseCase;
import io.mockify.hoster.usecase.UseCaseRequest;
import io.mockify.hoster.view.ProjectViewModelBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/api")
public class MainController {

    private final Repository repository;

    private final ProjectViewModelBuilder projectViewModelBuilder;

    private final ProjectCompilerUseCase projectCompilerUseCase;

    private final LoadProjectUseCase loadProjectUseCase;

    private final Security security;

    public MainController(Repository repository,
                          ProjectViewModelBuilder projectViewModelBuilder,
                          ProjectCompilerUseCase projectCompilerUseCase,
                          LoadProjectUseCase loadProjectUseCase, Security security) {
        this.repository = repository;
        this.projectViewModelBuilder = projectViewModelBuilder;
        this.projectCompilerUseCase = projectCompilerUseCase;
        this.loadProjectUseCase = loadProjectUseCase;
        this.security = security;
    }

    @GetMapping
    public String getHelloPage(Model model){
        model.addAttribute("name", "app");
        return "getHelloPage";//"<h1>Hello Page</h1>";
    }



    @GetMapping("/{projectName}/preview")
    public @ResponseBody
    String getHtml(@PathVariable("projectName") String projectName) {
        Project project = repository.load(projectName, security.getUserId());

        return projectCompilerUseCase.execute(project);
    }

    @GetMapping("/{projectName}")
    public @ResponseBody
    Project getProject(@PathVariable("projectName") String projectName) {
        return loadProjectUseCase.execute(new UseCaseRequest() {{
            setProjectName(projectName);
            setUserId(security.getUserId());
        }});
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/api/";
    }
}
