package io.mockify.hoster.usecase;

import io.mockify.hoster.constants.Constants;
import io.mockify.hoster.model.Project;
import io.mockify.hoster.model.Resource;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ProjectCompilerUseCase implements UseCase<Project, String> {

    @Override
    public String execute(Project project) {
        if (!(project != null && project.getTemplate() != null && project.getPostsList() != null)) return null;

        Document doc = Jsoup.parse(project.getTemplate().getHTMLdata());
        addPosts(project, doc);
        fillWithResources(doc, project);

        return doc.html();
    }

    private void addPosts(Project project, Document doc) {
        if(project.getTemplate().getContentTag() == null) return;

        Elements contentTag = doc.getElementsByTag(project.getTemplate().getContentTag());
        if (contentTag.hasText()) {
            final Element elementContent = contentTag.first();

            project.getPostsList().forEach(post -> {
                elementContent.append(post.getHtmlData());
            });
        }
    }

    private void fillWithResources(Document doc, Project project) {
        if(project.getResourceList() == null || project.getResourceList().isEmpty()) return;

        Elements resourceElements = doc.getElementsByAttribute(Constants.RESOURCE_HTML_ATTR_ID);

        resourceElements.forEach(e -> {
            Resource resource = project.getResource(
                    new Integer(e.attr(Constants.RESOURCE_HTML_ATTR_ID).toString())
            );

            if(e.hasAttr(Constants.RESOURCE_HTML_ATTR_URL))
                e.attributes().put("src",resource.getUrl());

        });
    }
}
