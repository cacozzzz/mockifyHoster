package io.mockify.hoster;

import io.mockify.hoster.enums.ResourceType;
import io.mockify.hoster.model.Post;
import io.mockify.hoster.model.Project;
import io.mockify.hoster.model.Resource;
import io.mockify.hoster.model.Template;
import io.mockify.hoster.model.dao.Repository;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class ProjectCompilerTest {

    private Repository repository = mock(Repository.class);
    private ProjectCompiler projectCompiler = new ProjectCompiler(repository);
    private String testHtmlFileData;

    @Before
    public void setUp(){
        try {
            byte[] bytes = Files.readAllBytes(
                    Paths.get(
                            System.getProperty("user.dir"),
                            "src",
                            "test",
                            "resources",
                            "index.html"
                    )
            );

            testHtmlFileData = new String(bytes);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getCompiledHtml() {
        Project project = getProject();
        assertEquals(projectCompiler.getCompiledHtml(project), testHtmlFileData);
    }

    @Test
    public void compile() throws Exception {

        Project project = getProject();
        doNothing().when(repository).saveHtml(any(), any());

        projectCompiler.compile(project);

        //String expected = "<html><head></head><body><content-tag><P>post1</P></content-tag></div></body></html>";
        verify(repository, times(1)).saveHtml(
                eq(
                        //Jsoup.parse(testHtmlFileData).html()
                        testHtmlFileData
                ),
                any());
    }

    @Test(expected = RuntimeException.class)
    public void compile_shouldFailBecauseOfException() throws Exception {
        doThrow(new RuntimeException("expected exception")).when(repository).saveHtml(any(), any());

        projectCompiler.compile(getProject());
    }

    @Test
    public void compile_notValidArgument() {
        projectCompiler.compile(new Project());

        verify(repository, never()).saveHtml(any(), any());
    }

    private Project getProject() {
        Project project = new Project(0);
        project.setName("TestProject");

        Post post = new Post();
        post.setId(0);
        post.setHtmlData("<P>post1</P><IMG data-resource-id=0 data-resource-url/>");
        post.setName("Post1");

        project.addPost(post);

        Template template = new Template();
        template.setId(0);
        template.setContentTag("content-tag");
        template.setHTMLdata("<html><head></head><body><content-tag/></div></body></html>");
        template.setName("template1");

        project.setTemplate(template);

        Resource resource = new Resource();
        resource.setId(0);
        resource.setName("pic1");
        resource.setType(ResourceType.IMAGE);
        resource.setUrl(
                Paths.get(System.getProperty("user.dir"),
                        "src",
                        "test",
                        "resources",
                        "Html",
                        "images001.jpg").toString()
        );

        project.getResourceList().add(resource);

        return project;
    }

}