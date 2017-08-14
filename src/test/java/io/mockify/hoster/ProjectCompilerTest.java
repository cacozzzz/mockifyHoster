package io.mockify.hoster;

import io.mockify.hoster.model.Post;
import io.mockify.hoster.model.Project;
import io.mockify.hoster.model.Template;
import io.mockify.hoster.model.dao.Repository;
import org.jsoup.Jsoup;
import org.junit.Test;

import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class ProjectCompilerTest {

    private Repository repository = mock(Repository.class);
    private ProjectCompiler projectCompiler = new ProjectCompiler(repository);

    @Test
    public void compile() throws Exception {

        Project project = getProject();
        doNothing().when(repository).saveHtml(any(), any());

        projectCompiler.compile(project);

        String expected = "<html><head></head><body><content-tag><P>post1</P></content-tag></div></body></html>";
        verify(repository, times(1)).saveHtml(
                eq(Jsoup.parse(expected).html()),
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
        post.setHtmlData("<P>post1</P>");
        post.setName("Post1");

        project.addPost(post);

        Template template = new Template();
        template.setId(0);
        template.setContentTag("content-tag");
        template.setHTMLdata("<html><head></head><body><content-tag/></div></body></html>");
        template.setName("template1");

        project.setTemplate(template);
        return project;
    }

}