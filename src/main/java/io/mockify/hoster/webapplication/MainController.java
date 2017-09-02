package io.mockify.hoster.webapplication;

import io.mockify.hoster.ProjectCompiler;
import io.mockify.hoster.model.Project;
import io.mockify.hoster.model.dao.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api")
public class MainController {

    private final Repository repository;
    @Autowired
    private ProjectCompiler projectCompiler;

    public MainController(Repository repository) {
        this.repository = repository;
    }


    @GetMapping("/{userId}/{projectName}/preview")
    public @ResponseBody
    String getHtml(@PathVariable("projectName") String projectName, @PathVariable("userId") String userId) {
        return projectCompiler.getCompiledHtml(repository.load(projectName, userId));
    }

    @GetMapping("/{userId}/{projectName}")
    public @ResponseBody
    Project getProject(@PathVariable("projectName") String projectName, @PathVariable("userId") String userId) {
        return repository.load(projectName, userId);
    }


}
