package io.mockify.hoster;

import io.mockify.hoster.model.Post;
import io.mockify.hoster.model.Project;
import io.mockify.hoster.model.Template;
import org.junit.Test;

public class ProjectCompilerTest {
    @Test
    public void compile() throws Exception {

        //Project project = new Project(0);
        //project.setName("TestProject");
//
        //Post post = new Post();
        //post.setId(0);
        //post.setHtmlData("<P>post1</P>");
        //post.setName("Post1");
//
        //project.addPost(post);
//
        //Template template = new Template();
        //template.setId(0);
        //template.setContentTag("content-tag");
        //template.setHTMLdata("<html><head></head><body><content-tag/></div></body></html>");
        //template.setName("template1");

        Project  project = ProjectCompiler.loadProject("TestProject");

        ProjectCompiler.compile(project);


    }

}