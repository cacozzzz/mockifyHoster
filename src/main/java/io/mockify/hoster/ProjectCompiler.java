package io.mockify.hoster;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.mockify.hoster.constants.Constants;
import io.mockify.hoster.model.Project;
import io.mockify.hoster.model.Resource;
import io.mockify.hoster.model.dao.Repository;
import jdk.nashorn.internal.parser.JSONParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

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

            doc = fillWithResources(doc, project);

            repository.saveHtml(doc.html(), project);
        }
    }

    private Document fillWithResources(Document doc, Project project) {

        Elements resourceElements = doc.getElementsByAttribute(Constants.RESOURCE_HTML_ATTR_ID);

        resourceElements.forEach(e -> {

            Resource resource = project.getResource(
                    new Integer(e.attr(Constants.RESOURCE_HTML_ATTR_ID).toString())
            );


            if(e.hasAttr(Constants.RESOURCE_HTML_ATTR_URL))
                e.attributes().put("src",resource.getUrl());

            System.out.println(e.html());
        });

        return doc;
    }


}
