package io.mockify.hoster;

import io.mockify.hoster.model.Project;
import io.mockify.hoster.model.dao.Repository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class ProjectCompiler {

    private Repository repository;

    public ProjectCompiler(Repository repository) {
        this.repository = repository;
    }

    public void compile(Project project){

        if (project != null && project.getTemplate() != null && project.getPostsList() != null) {
            Document doc = Jsoup.parse(project.getTemplate().getHTMLdata());


            final Element elementContent = doc.getElementsByTag(project.getTemplate().getContentTag()).first();

            project.getPostsList().forEach(post -> {
                elementContent.append(post.getHtmlData());
            });

            repository.saveHtml(doc.html(), project);
        }

    }





}
