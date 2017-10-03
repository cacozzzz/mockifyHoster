package io.mockify.hoster.usecase;

import io.mockify.hoster.model.Project;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureWebMvc
public class LoadRemoteTemplateFromUrlUseCaseTest {

    @Autowired
    private SaveProjectUseCase projectSaverUseCase;
    @Autowired
    private LoadRemoteTemplateFromUrlUseCase remoteTemplateLoaderUseCase;

    @Test
    public void execute() throws Exception {
        Project project = new Project(0);

        project.setName("sarbiz");
        remoteTemplateLoaderUseCase.execute(new UseCaseRequest() {
            {
                setProject(project);
                setRemoteTemplateUrl("http://astra-spb.webflow.io/");
            }
        });

        projectSaverUseCase.execute(new UseCaseRequest() {
            {
                setProject(project);
                setUserId("test@test.com");
            }
        });
    }
}