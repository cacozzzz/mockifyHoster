package io.mockify.hoster.webapplication;

import io.mockify.hoster.usecase.LoadProjectUseCase;
import io.mockify.hoster.usecase.ProjectCompilerUseCase;
import io.mockify.hoster.model.Project;
import io.mockify.hoster.dao.Repository;
import io.mockify.hoster.usecase.UseCaseRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api")
public class MainController {

    private final Repository repository;

    private final ProjectCompilerUseCase projectCompilerUseCase;

    private final LoadProjectUseCase loadProjectUseCase;

    private final Security security;

    public MainController(Repository repository,
                          ProjectCompilerUseCase projectCompilerUseCase,
                          LoadProjectUseCase loadProjectUseCase, Security security) {
        this.repository = repository;
        this.projectCompilerUseCase = projectCompilerUseCase;
        this.loadProjectUseCase = loadProjectUseCase;
        this.security = security;
    }

    @GetMapping
    public @ResponseBody String getHelloPage(){
        return "<h1>Hello Page</h1>";
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

}
